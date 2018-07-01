package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantDoubleInfo implements ConstantInfo {
    double val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64().doubleValue();
    }

    public double getValue() {
        return val;
    }
}
