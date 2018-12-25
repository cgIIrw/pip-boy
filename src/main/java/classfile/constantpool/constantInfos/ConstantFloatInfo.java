package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantFloatInfo implements ConstantInfo {
    private final int tag = 4;
    private float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint32();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public float getValue() {
        return val;
    }
}
