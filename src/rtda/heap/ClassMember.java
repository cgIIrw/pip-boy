package rtda.heap;

import classfile.MemberInfo;

public class ClassMember {
    int accessFlags;
    String name;
    String descriptor;
    // 可以通过方法或字段访问到它所属的类
    Myclass myclass;

    public ClassMember(Myclass myclass, MemberInfo classFileMemberInfo) {
        copyMemberInfo(classFileMemberInfo);
        this.myclass = myclass;
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

    public Myclass getMyclass() {
        return myclass;
    }

    // myclass1是否可以访问myclass
    public boolean isAccessibleTo(Myclass myclass1) {
        if (this.isPublic()) {
            return true;
        }
        // 不同包下相同的类是否需要考虑？或者说自己是否为自己的子类？不吝啬打括号
        if (this.isProtected()) {
            return (myclass1 == myclass) || myclass1.isSubClassOf(myclass)
                    || (myclass1.getPackageName() == myclass.getPackageName());
        }

        // 这是默认包访问
        if (!this.isPrivate()) {
            return myclass1.getPackageName() == myclass.getPackageName();
        }

        return myclass1 == myclass;
        // 是否没考虑内部类？
    }
}
