package rtda.heap;

import classfile.ConstantInfo;

public abstract class MemberRef extends SymRef{

    String name;
    String descriptor;

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
}
