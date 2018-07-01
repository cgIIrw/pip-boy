package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantNameAndTypeInfo implements ConstantInfo {
    public static final int id = 12;
    private int nameIndex;
    private int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public int getId() {
        return id;
    }

    public int getNameIndex() {
        return this.nameIndex;
    }

    public int getDescriptorIndex() {
        return this.descriptorIndex;
    }
}
