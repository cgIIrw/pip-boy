package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;
import classfile.constantpool.ConstantPool;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantStringInfo implements ConstantInfo {
    private final int tag = 8;
    private ConstantPool cp;
    private int stringIndex;

    public ConstantStringInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public String string() {
        return this.cp.getUtf8(this.stringIndex);
    }
}
