package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef extends MemberRef {
    private MyMethod method;


    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, ConstantInterfaceMethodrefInfo refInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(refInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantInterfaceMethodrefInfo)info).getClassName());
        setName(((ConstantInterfaceMethodrefInfo)info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantInterfaceMethodrefInfo)info).getNameAndDescriptor()[1]);
    }

    public MyMethod resolvedInterfaceMethod() {
        if (this.method == null) {
            resolvedInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolvedInterfaceMethodRef() {
        Myclass d = this.getRuntimeConstantPool().getMyclass();
        Myclass c = this.resolvedClass();

        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        MyMethod method = lookupInterfaceMethod(c, this.getName(), this.getDescriptor());
        if (method == null) {
            throw new NoSuchMethodError();
        }

        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    public MyMethod lookupInterfaceMethod(Myclass iface, String name, String descriptor) {
        for (MyMethod method : iface.getMethods()) {
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }
        return lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
    }
}
