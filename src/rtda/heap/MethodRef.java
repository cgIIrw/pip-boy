package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantMethodrefInfo;

public class MethodRef extends MemberRef {
    private MyMethod method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool,  ConstantMethodrefInfo methodrefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(methodrefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantMethodrefInfo)info).getClassName());
        setName(((ConstantMethodrefInfo)info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantMethodrefInfo)info).getNameAndDescriptor()[1]);
    }

    public MyMethod resolvedMethod() {
        if (this.method == null) {
            resolveMethodRef();
        }
        return this.method;
    }

    public void resolveMethodRef() {
        Myclass d = this.getRuntimeConstantPool().getMyclass();
        Myclass c = this.resolvedClass();

        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        MyMethod method = lookupMethod(c, this.getName(), this.getDescriptor());

        if (method == null) {
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    public MyMethod lookupMethod(Myclass myclass, String name, String descriptor) {
        MyMethod method = lookupMethodInClass(myclass, name, descriptor);
        if (method == null) {
            method = lookupMethodInInterfaces(myclass.getInterfaces(), name, descriptor);
        }
        return method;
    }

    public static MyMethod lookupMethodInClass(Myclass myclass, String name, String descriptor) {
        for (Myclass c = myclass; c != null; c = c.getSuperClass()) {
            for (MyMethod method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }
}
