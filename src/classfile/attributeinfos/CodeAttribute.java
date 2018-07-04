package classfile.attributeinfos;

import classfile.AttributeInfo;
import classfile.ClassReader;
import classfile.ConstantPool;
import classfile.GreateAttributeInfo;

/**
 * Created by yin on 18/4/17.
 */
public class CodeAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int maxStack;
    private int maxLocals;
    private byte[] code;
    ExceptionTableEntry[] exceptionTable;
    AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }


    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        int codeLength = (int)(reader.readUint32());
        this.code = reader.readBytes(codeLength);
        this.exceptionTable = ExceptionTableEntry.readExceptionTable(reader);
        this.attributes = GreateAttributeInfo.readAttributes(reader, this.cp);
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public byte[] getCode() {
        return this.code;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return this.exceptionTable;
    }


}

class ExceptionTableEntry {
    private int startPc;
    private int endPc;
    private int handlerPc;
    private int catchType;

    public ExceptionTableEntry(ClassReader reader) {
        startPc = reader.readUint16();
        endPc = reader.readUint16();
        handlerPc = reader.readUint16();
        catchType = reader.readUint16();

    }

    static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
        int exceptionTableLength = reader.readUint16();
        ExceptionTableEntry[] exceptionTable = new ExceptionTableEntry[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionTableEntry(reader);
        }
        return exceptionTable;
    }

    int getStartPc() {
        return this.startPc;
    }

    int getEndPc() {
        return this.endPc;
    }

    int getHandlerPc() {
        return this.handlerPc;
    }

    int getCatchType() {
        return this.catchType;
    }
}
