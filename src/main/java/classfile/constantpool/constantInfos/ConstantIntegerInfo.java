package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantIntegerInfo implements ConstantInfo {
    private final int tag = 3;
    private int val;

    @Override
    public void readInfo(ClassReader reader) {
        val = (int) (reader.readUint32());
    }

    @Override
    public int getTag() {
        return tag;
    }

    public int getValue() {
        return val;
    }
}
