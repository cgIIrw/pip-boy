package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantInterfaceMethodrefInfo;

public class InterfaceMethodRef extends MemberRef {


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
}
