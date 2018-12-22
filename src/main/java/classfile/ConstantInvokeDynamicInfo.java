package classfile;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantInvokeDynamicInfo implements ConstantInfo {
    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.bootstrapMethodAttrIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }
}
