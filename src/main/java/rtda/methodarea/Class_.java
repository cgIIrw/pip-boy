package rtda.methodarea;

import classfile.ClassFile;
import rtda.heap.*;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.LocalVars_;
import rtda.utils.AccessFlags;

public class Class_ {
    //
    private int accessFlags;
    //当前类名(完全限定名)
    private String thisClassName;
    //父类名(完全限定名)
    private String superClassName;
    //接口名(完全限定名)
    private String[] interfaceNames;
    //运行时常量池
    private RuntimeConstantPool_ runtimeConstantPool;
    // 字段集合
    private Field_[] fields;
    // 方法集合
    private Method_[] methods;
    // 当前的类加载器
    private ClassLoader_ loader;
    // 父类的Class
    private Class_ superClass;
    // 被继承的接口的Class
    private Class_[] interfaces;

    private int instanceSlotCount;

    private int staticSlotCount;

    private LocalVars_ staticVars;

    private boolean clinitedFlag = false;

    // name是完全限定名
    // Java虚拟机运行时产生数组类的构造器
    public Class_(String name, ClassLoader_ classLoader) {
        this.accessFlags = AccessFlags.ACC_PUBLIC;
        this.thisClassName = name;
        this.loader = classLoader;
        this.clinitedFlag = true;
        this.superClass = classLoader.loadClass("java/lang/Object");
        this.interfaces = new Class_[]{classLoader.loadClass("java/lang/Cloneable"),
                classLoader.loadClass("java/io/Serializable")};
    }

    public Class_(ClassFile cf) {
        this.accessFlags = cf.getAccessFlags();
        this.thisClassName = cf.getClassName();
        this.superClassName = cf.getSuperClassName();
        this.interfaceNames = cf.getInterfaceNames();
        this.runtimeConstantPool = new RuntimeConstantPool_(this, cf.getConstantPool());
        // 由于返回数组，这里使用一个静态方法获取
        this.fields = Field_.newFields(this, cf.getFields());
        // 由于返回数组，这里使用一个静态方法获取
        this.methods = Method_.newMethods(this, cf.getMethods());
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

    public String getPackageName() {
        int i = this.thisClassName.lastIndexOf("/");
        if (i > 0) {
            return this.thisClassName.substring(0, i);
        }
        return "";
    }

    public boolean isSuperClassOf(Class_ other) {
        return other.isSubClassOf(this);
    }

    public boolean isSubClassOf(Class_ class_) {

        for (Class_ c = this.superClass; c != null; c = c.superClass) {
            if (c == class_) {
                return true;
            }
        }
        return false;
    }

    public boolean isSubInterfaceOf(Class_ iface) {
        for (Class_ superInterface : this.interfaces) {
            if (superInterface == iface || superInterface.isSubInterfaceOf(iface)) {
                return true;
            }
        }
        return false;
    }

    public boolean isImplements(Class_ iface) {
        for (Class_ c = this; c != null; c = c.superClass) {
            for (Class_ i : c.interfaces) {
                if (i == iface || i.isSubInterfaceOf(iface)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAssignableFrom(Class_ other) {
        // 判断other是不是this的子类或者继承自this
        Class_ t = this;

        if (t == other) {
            return true;
        }

        if (!t.isInterface()) {
            return other.isSubClassOf(t);
        } else {
            return other.isImplements(t);
        }
    }

    //
    public boolean isAccessibleTo(Class_ otherclass) {
        return this.isPublic() || (this.getPackageName().equals(otherclass.getPackageName()));
    }

    public Instance_ newObject() {
        return new Instance_(this);
    }

    public LocalVars_ getStaticVars() {
        return staticVars;
    }

    public RuntimeConstantPool_ getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public String getThisClassName() {
        return thisClassName;
    }

    public String getSuperClassName() {
        return superClassName;
    }

    public String[] getInterfaceNames() {
        return interfaceNames;
    }

    public Field_[] getFields() {
        return fields;
    }

    public Method_[] getMethods() {
        return methods;
    }

    public ClassLoader_ getLoader() {
        return loader;
    }

    public Class_ getSuperClass() {
        return superClass;
    }

    public Class_[] getInterfaces() {
        return interfaces;
    }

    public int getInstanceSlotCount() {
        return instanceSlotCount;
    }

    public int getStaticSlotCount() {
        return staticSlotCount;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public void setThisClassName(String thisClassName) {
        this.thisClassName = thisClassName;
    }

    public void setSuperClassName(String superClassName) {
        this.superClassName = superClassName;
    }

    public void setInterfaceNames(String[] interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public void setRuntimeConstantPool(RuntimeConstantPool_ runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    public void setFields(Field_[] fields) {
        this.fields = fields;
    }

    public void setMethods(Method_[] methods) {
        this.methods = methods;
    }

    public void setLoader(ClassLoader_ loader) {
        this.loader = loader;
    }

    public void setSuperClass(Class_ superClass) {
        this.superClass = superClass;
    }

    public void setInterfaces(Class_[] interfaces) {
        this.interfaces = interfaces;
    }

    public void setInstanceSlotCount(int instanceSlotCount) {
        this.instanceSlotCount = instanceSlotCount;
    }

    public void setStaticSlotCount(int staticSlotCount) {
        this.staticSlotCount = staticSlotCount;
    }

    public void setStaticVars(LocalVars_ staticVars) {
        this.staticVars = staticVars;
    }


    public Method_ getStaticMethod(String name, String descriptor) {
        for (Method_ method : this.methods) {
            if (method.isStatic() && method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return null;
    }

    public boolean getClinitFlag() {
        return clinitedFlag;
    }

    public void setClinitedFlag(boolean clinitedFlag) {
        this.clinitedFlag = clinitedFlag;
    }

    public boolean isArray() {
        return this.thisClassName.startsWith("[");
    }

    // 为新创建的数组开辟内存
    public Instance_ newArray(int count) {
        if (!isArray())
            throw new RuntimeException("该对象不是数组！");
        switch (thisClassName) {
            case "[Z":
            case "[B":
            case "[C":
            case "[S":
            case "[I":
            case "[F":
                return new Instance_(this, count);
            case "[D":
            case "[J":
                return new Instance_(this, 2 * count);
            default:
                return new Instance_(this, count);
        }
    }
}
