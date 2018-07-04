package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantMethodHandleInfo implements ConstantInfo {
    private final int id = 15;
    int referenceKind;
    int referenceIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.referenceKind = reader.readUint8();
        this.referenceIndex = reader.readUint16();
    }

    @Override
    public int getId() {
        return id;
    }
}
