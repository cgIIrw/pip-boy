package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantIntegerInfo implements ConstantInfo {
    private final int id = 3;
    int val;

    @Override
    public void readInfo(ClassReader reader) {
         long temp = reader.readUint32();
        this.val = ((int) temp);
    }

    @Override
    public int getId() {
        return id;
    }

    public int getValue() {
        return val;
    }
}
