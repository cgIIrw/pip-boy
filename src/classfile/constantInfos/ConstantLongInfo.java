package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

import java.math.BigInteger;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantLongInfo implements ConstantInfo {
    BigInteger val;
    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64();
    }

    public BigInteger getValue() {
        return val;
    }
}
