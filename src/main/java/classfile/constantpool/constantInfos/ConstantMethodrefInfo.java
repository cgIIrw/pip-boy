package classfile.constantpool.constantInfos;

import classfile.utils.ClassReader;
import classfile.constantpool.ConstantInfo;
import classfile.constantpool.ConstantPool;

/**
 * Created by cgrw on 18/4/17.
 */
public class ConstantMethodrefInfo implements ConstantInfo {
    private final int tag = 10;
    private ConstantPool cp;
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantMethodrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public int getTag() {
        return tag;
    }

    public String getClassName() {
        return this.cp.getClassName(this.classIndex);
    }

    public String[] getNameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }
}
