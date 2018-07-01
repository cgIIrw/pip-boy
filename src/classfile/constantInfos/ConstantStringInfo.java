package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;
import classfile.ConstantPool;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantStringInfo implements ConstantInfo {
    private ConstantPool cp;
    private int stringIndex;

    public ConstantStringInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.stringIndex = reader.readUint16();
    }

    public String string() {
        return this.cp.getUtf8(this.stringIndex);
    }
}
