package classfile;

import classfile.attributeinfo.AttributeInfo;
import classfile.attributeinfo.CreateAttributeInfo;
import classfile.constantpool.ConstantPool;
import classfile.memberinfo.MemberInfo;
import classfile.utils.ClassReader;

/**
 * @author cgIIrw
 * @date 2018/4/11
 */
// 每一个Class文件对应一个classFile结构体，
// 它是对Class文件的抽象
public class ClassFile {
    private int minorVersion;
    private int majorVersion;
    private ConstantPool constantPool;
    private int accessFlags;
    private int thisClass;
    private int superClass;
    private int[] interfaces;
    private MemberInfo[] fields;
    private MemberInfo[] methods;
    private AttributeInfo[] attributes;

    private static final ClassFile INSTANCE = new ClassFile();

    // 解析，使用静态工厂方法
    public static ClassFile Parse(byte[] classData) {
        ClassReader cr = new ClassReader(classData);
        INSTANCE.read(cr);
        return INSTANCE;
    }

    private void read(ClassReader reader) {
//        MemberInfo mb = new MemberInfo();
        readAndCheckMagic(reader);
        readAndCheckVersion(reader);
        constantPool = new ConstantPool();
        constantPool.readConstantPool(reader);
        accessFlags = reader.readUint16();
        thisClass = reader.readUint16();
        superClass = reader.readUint16();
        interfaces = reader.readUint16s();
        fields = MemberInfo.readMembers(reader, constantPool);
        methods = MemberInfo.readMembers(reader, constantPool);
        attributes = CreateAttributeInfo.readAttributes(reader, constantPool);
    }

    // 验证魔数
    private void readAndCheckMagic(ClassReader reader) {
        long magic = reader.readUint32();
        if (magic != 0xCAFEBABE) {
            throw new RuntimeException("java.lang.ClassFormatError: magic!");
        }
    }

    // 读取并验证版本
    private void readAndCheckVersion(ClassReader reader) {
        this.minorVersion = reader.readUint16();
        this.majorVersion = reader.readUint16();
        switch (majorVersion) {
            // 1.2之前主版本号都是45
            case 45:
                return;
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
                // 次版本号只在1.2之前用过，从1.2开始剧本上都没什么用了(都是0)
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
        // super_class的值必须为0或者是对constantPool表中项目的一个有效索引值
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
