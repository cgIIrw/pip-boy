package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

import java.math.BigInteger;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantLongInfo implements ConstantInfo {
    public static final int id = 5;
    BigInteger val;
    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64();
    }

    @Override
    public int getId() {
        return id;
    }

    public BigInteger getValue() {
        return val;
    }
}
