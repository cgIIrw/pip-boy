package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by cgrw on 18/4/17.
 */
public class ConstantMethodHandleInfo implements ConstantInfo {
    private final int tag = 15;
    private int referenceKind;
    private int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public int getReferenceKind() {
        return referenceKind;
    }

    public int getReferenceIndex() {
        return referenceIndex;
    }
}
