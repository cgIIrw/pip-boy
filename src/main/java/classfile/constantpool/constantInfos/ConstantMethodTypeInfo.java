package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    private final int tag = 16;
    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
