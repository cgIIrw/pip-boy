package rtda.heap;

import classfile.ClassFile;

public class Myclass {
    //
    int accessFlags;
    //当前类名(完全限定名)
    public String name;
    //父类名(完全限定名)
    public String superClassName;
    //接口名(完全限定名)
    public String[] interfaceNames;
    //运行时常量池
    public RuntimeConstantPool runtimeConstantPool;
    // 字段表
    public MyField[] fields;
    // 方法表
    public MyMethod[] methods;

    public Myclass(ClassFile cf) {
        this.accessFlags = cf.getAccessFlags();
        this.name = cf.getClassName();
        this.superClassName = cf.getSuperClassName();
        this.interfaceNames = cf.getInterfaceNames();
        this.runtimeConstantPool = new RuntimeConstantPool(this, cf.getConstantPool());
        // 由于返回数组，这里使用一个静态方法获取
        this.fields = MyField.newFields(this, cf.getFields());
        // 由于返回数组，这里使用一个静态方法获取
        this.methods = MyMethod.newMethods(this, cf.getMethods());
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSuper() {
        return 0 != (accessFlags & AccessFlags.ACC_SUPER);
    }

    public boolean isInterface() {
        return 0 != (accessFlags & AccessFlags.ACC_INTERFACE);
    }

    public boolean isAbstract() {
        return 0 != (accessFlags & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public boolean isAnnotation() {
        return 0 != (accessFlags & AccessFlags.ACC_ANNOTATION);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlags.ACC_ENUM);
    }
}