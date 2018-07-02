package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantFieldrefInfo;

public class FieldRef extends MemberRef{


    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldrefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        className = ((ConstantFieldrefInfo)info).getClassName();
        name = ((ConstantFieldrefInfo)info).getNameAndDescriptor()[0];
        descriptor = ((ConstantFieldrefInfo)info).getNameAndDescriptor()[1];
    }
}
