package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantMethodTypeInfo implements ConstantInfo {
    public static final int id = 16;
    int descriptorIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.descriptorIndex = reader.readUint16();
    }

    @Override
    public int getId() {
        return id;
    }
}
