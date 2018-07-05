package rtda.heap;

import classfile.ConstantInfo;

public abstract class MemberRef extends SymRef{

    private String name;
    private String descriptor;

    public MemberRef(RuntimeConstantPool runtimeConstantPool) {
        super(runtimeConstantPool);
    }

    abstract void copyMemberRefInfo(ConstantInfo info);

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public MyMethod lookupMethodInInterfaces(Myclass[] ifaces, String name, String descriptor) {
        for (Myclass iface : ifaces) {
            for (MyMethod method : iface.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }

            MyMethod method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
