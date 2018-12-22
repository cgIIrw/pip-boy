package classfile;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantMethodHandleInfo implements ConstantInfo {
    int referenceKind;
    int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }
}
