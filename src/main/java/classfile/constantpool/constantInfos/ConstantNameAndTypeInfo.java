package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by cgrw on 18/4/16.
 */
public class ConstantNameAndTypeInfo implements ConstantInfo {
    private final int tag = 12;
    private int nameIndex;
    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }
}
