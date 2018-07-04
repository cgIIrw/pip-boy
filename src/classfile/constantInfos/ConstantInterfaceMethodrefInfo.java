package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;
import classfile.ConstantPool;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantInterfaceMethodrefInfo implements ConstantInfo {
    private final int id = 11;
    private ConstantPool cp;
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantInterfaceMethodrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    @Override
    public int getId() {
        return id;
    }

    public String getClassName() {
        return this.cp.getClassName(this.classIndex);
    }

    public String[] getNameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }
}
