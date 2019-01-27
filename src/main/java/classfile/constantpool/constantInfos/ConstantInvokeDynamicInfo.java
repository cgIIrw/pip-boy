package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {
    private final int tag = 18;
    private int bootstrapMethodAttrIndex;
    private int nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
