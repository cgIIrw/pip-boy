package classfile;

/**
 * @author cgIIrw
 * @date 2018/4/11
 */
public class ClassFile {
    int minorVersion;
    int majorVersion;
    ConstantPool constantPool;
    int accessFlags;
    int thisClass;
    int superClass;
    int[] interfaces;
    MemberInfo[] fields;
    MemberInfo[] methods;
    AttributeInfo[] attributes;


    // 解析
    public static ClassFile Parse(byte[] classData) {
        ClassReader cr = new ClassReader(classData);
        ClassFile cf = new ClassFile();
        cf.read(cr);
        return cf;
    }

    public void read(ClassReader reader) {
        MemberInfo mb = new MemberInfo();
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = new ConstantPool();
        constantPool.readConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = mb.readMembers(reader, constantPool);
        methods = mb.readMembers(reader, constantPool);
        attributes = readAttributes(reader, constantPool);
    }

    public void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readUint32();
        if (magic != 0xCAFEBABE) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
    }

    public void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (majorVersion) {
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                if (this.minorVersion == 0) {
                    return;
                }
        }
        throw new RuntimeException("java.lang.UnsupportedClassVersionError!");
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public String getClassName() {
        return constantPool.getClassName(thisClass);
    }

    public String getSuperClassName() {
        // 没有超类的情况也要考虑 例如当类为java.lang.Object时
        if (superClass > 0) {
            return constantPool.getClassName(superClass);
        }
        return "";
    }

    public String[] getInterfaceNames() {
        String[] interfaceNames = new String[interfaces.length];
        for (int i = 0; i < interfaceNames.length; i++) {
            interfaceNames[i] = constantPool.getClassName(interfaces[i]);
        }
        return interfaceNames;
    }
}
