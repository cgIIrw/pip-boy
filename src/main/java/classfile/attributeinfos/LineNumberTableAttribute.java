package classfile.attributeinfos;

import classfile.AttributeInfo;
import classfile.ClassReader;

/**
 * Created by yin on 18/4/22.
 */
public class LineNumberTableAttribute implements AttributeInfo {
    LineNumberTableEntry[] lineNumberTable;
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
    int startPc;
    int lineNumber;

    public LineNumberTableEntry(ClassReader reader) {
        startPc = reader.readUint16();
        lineNumber = reader.readUint16();
    }
}
