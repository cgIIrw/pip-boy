package classfile.attributeinfo.attributeinfos;

import classfile.attributeinfo.AttributeInfo;
import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/22.
 */
public class LineNumberTableAttribute implements AttributeInfo {
    private LineNumberTableEntry[] lineNumberTable;
    @Override
    public void readInfo(ClassReader reader) {
        int lineNumberTableLength = reader.readUint16();
        this.lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            this.lineNumberTable[i] = new LineNumberTableEntry(reader);
        }
    }
}

class LineNumberTableEntry {
    private int startPc;
    private int lineNumber;

    public LineNumberTableEntry(ClassReader reader) {
        startPc = reader.readUint16();
        lineNumber = reader.readUint16();
    }
}
