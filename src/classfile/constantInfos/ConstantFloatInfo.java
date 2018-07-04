package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantFloatInfo implements ConstantInfo {
    private final int id = 4;
    float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint32();
    }

    @Override
    public int getId() {
        return id;
    }

    public float getValue() {
        return val;
    }
}
