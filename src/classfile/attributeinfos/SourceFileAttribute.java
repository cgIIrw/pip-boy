package classfile.attributeinfos;

import classfile.AttributeInfo;
import classfile.ClassReader;
import classfile.ConstantPool;

/**
 * Created by yin on 18/4/22.
 */
public class SourceFileAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int sourceFileIndex;

    public SourceFileAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.sourceFileIndex = reader.readUint16();
    }

    String getFileName() {
        return this.cp.getUtf8(this.sourceFileIndex);
    }
}
