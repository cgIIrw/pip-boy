package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;
import classfile.constantpool.ConstantPool;

/**
 * Created by cgrw on 18/4/16.
 */
public class ConstantClassInfo implements ConstantInfo {
    private final int tag = 7;
    private ConstantPool cp;
    private int nameIndex;

    public ConstantClassInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.nameIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public String getName() {
        return this.cp.getUtf8(this.nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
