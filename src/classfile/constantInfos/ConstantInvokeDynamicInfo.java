package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {
    private final int id = 18;
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public int getId() {
        return id;
    }
}
