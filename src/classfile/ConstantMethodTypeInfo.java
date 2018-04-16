package classfile;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }
}
