package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantIntegerInfo implements ConstantInfo {
    long val;

    @Override
    public void readInfo(ClassReader reader) {
        this.val = reader.readUint32();
    }

    public long getValue() {
        return val;
    }
}
