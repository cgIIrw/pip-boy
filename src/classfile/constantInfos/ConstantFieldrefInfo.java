package classfile.constantInfos;

import classfile.ClassReader;
import classfile.ConstantInfo;
import classfile.ConstantPool;

/**
 * Created by yin on 18/4/17.
 */
public class ConstantFieldrefInfo implements ConstantInfo {
    private ConstantPool cp;
    private int classIndex;
    private int nameAndTypeIndex;

    public ConstantFieldrefInfo(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.classIndex = reader.readUint16();
        this.nameAndTypeIndex = reader.readUint16();
    }

    public String getClassName() {
        return this.cp.getClassName(this.classIndex);
    }

    public String[] NameAndDescriptor() {
        return this.cp.getNameAndType(this.nameAndTypeIndex);
    }

}
