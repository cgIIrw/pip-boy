package classfile;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantFloatInfo implements ConstantInfo {
    float val;

    @Override
    public void readInfo(ClassReader reader) {
        val = reader.readUint32();
    }

    public float getValue() {
        return val;
    }
}
