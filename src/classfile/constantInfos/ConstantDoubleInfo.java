package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantDoubleInfo implements ConstantInfo {
    private final int id = 6;
    double val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64().doubleValue();
    }

    @Override
    public int getId() {
        return id;
    }

    public double getValue() {
        return val;
    }
}
