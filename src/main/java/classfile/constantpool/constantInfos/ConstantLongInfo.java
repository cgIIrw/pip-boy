package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;

import java.math.BigInteger;

/**
 * Created by cgrw on 18/4/16.
 */
public class ConstantLongInfo implements ConstantInfo {
    private final int tag = 5;
    private BigInteger val;
    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint64();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public BigInteger getValue() {
        return val;
    }
}
