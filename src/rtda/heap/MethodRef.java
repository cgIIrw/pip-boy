package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantMethodrefInfo;

public class MethodRef extends MemberRef {
    public MethodRef(RuntimeConstantPool runtimeConstantPool,  ConstantMethodrefInfo methodrefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(methodrefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantMethodrefInfo)info).getClassName());
        name = ((ConstantMethodrefInfo)info).getNameAndDescriptor()[0];
        descriptor = ((ConstantMethodrefInfo)info).getNameAndDescriptor()[1];
    }
}
