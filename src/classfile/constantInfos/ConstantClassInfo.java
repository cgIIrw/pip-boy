package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;
import classfile.ConstantPool;

/**
 * Created by yin on 18/4/16.
 */
public class ConstantClassInfo implements ConstantInfo {
    private final int id = 7;
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
    public int getId() {
        return id;
    }

    public String getName() {
        return this.cp.getUtf8(this.nameIndex);
    }

    public int getNameIndex() {
        return nameIndex;
    }
}
