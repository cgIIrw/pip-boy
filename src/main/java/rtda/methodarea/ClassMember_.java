package rtda.methodarea;

import classfile.memberinfo.MemberInfo;
import rtda.utils.AccessFlags;

// 字段和方法的公共类
public class ClassMember_ {
    private int accessFlags;
    private String name;
    private String descriptor;
    // 可以通过方法或字段访问到它所属的类
    private InstanceKlass_ instanceKlass_;

    public ClassMember_(InstanceKlass_ instanceKlass_, MemberInfo memberInfo) {
        this.accessFlags = memberInfo.getAccessFlags();
        this.name = memberInfo.getName();
        this.descriptor = memberInfo.getDescriptor();
        this.instanceKlass_ = instanceKlass_;
    }

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isSynthetic() {
        return 0 != (accessFlags & AccessFlags.ACC_SYNTHETIC);
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public InstanceKlass_ getInstanceKlass_() {
        return instanceKlass_;
    }

    // class1_是否可以访问class_
    public boolean isAccessibleTo(InstanceKlass_ instanceKlass_1) {
        // 如果被访问的类是public当然可以被访问
        if (this.isPublic()) {
            return true;
        }
        // 不同包下相同的类是否需要考虑？或者说自己是否为自己的子类？不吝啬打括号
        if (this.isProtected()) {
            return (instanceKlass_1 == instanceKlass_) || instanceKlass_1.isSubClassOf(instanceKlass_)
                    || (instanceKlass_1.getPackageName().equals(instanceKlass_.getPackageName()));
        }

        // 这是默认包访问
        if (!this.isPrivate()) {
            return instanceKlass_1.getPackageName().equals(instanceKlass_.getPackageName());
        }

        return instanceKlass_1 == instanceKlass_;
    }

    public int getAccessFlags() {
        return accessFlags;
    }

    public void setAccessFlags(int accessFlags) {
        this.accessFlags = accessFlags;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public void setThisClass(InstanceKlass_ instanceKlass_) {
        this.instanceKlass_ = instanceKlass_;
    }
}
