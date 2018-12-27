package rtda.heap;

import classfile.memberinfo.MemberInfo;

public class ClassMember {
    private int accessFlags;
    private String name;
    private String descriptor;
    // 可以通过方法或字段访问到它所属的类
    private Class_ class_;

    public ClassMember(Class_ class_, MemberInfo classFileMemberInfo) {
        copyMemberInfo(classFileMemberInfo);
        this.class_ = class_;
    }

    private void copyMemberInfo(MemberInfo memberInfo) {
        accessFlags = memberInfo.getAccessFlags();
        name = memberInfo.getName();
        descriptor = memberInfo.getDescriptor();
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

    public Class_ getClass_() {
        return class_;
    }

    // myclass1是否可以访问myclass
    public boolean isAccessibleTo(Class_ myclass1) {
        if (this.isPublic()) {
            return true;
        }
        // 不同包下相同的类是否需要考虑？或者说自己是否为自己的子类？不吝啬打括号
        if (this.isProtected()) {
            return (myclass1 == class_) || myclass1.isSubClassOf(class_)
                    || (myclass1.getPackageName() == class_.getPackageName());
        }

        // 这是默认包访问
        if (!this.isPrivate()) {
            return myclass1.getPackageName() == class_.getPackageName();
        }

        return myclass1 == class_;
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

    public void setMyclass(Class_ class_) {
        this.class_ = class_;
    }
}
