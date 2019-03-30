package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

/**
 * Created by cgrw on 18/4/16.
 */
public class ConstantDoubleInfo implements ConstantInfo {
    private final int tag = 6;
    double val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64().doubleValue();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public double getValue() {
        return val;
    }
}
