package classfile.attributeinfos;

import classfile.AttributeInfo;
import classfile.ClassReader;
import classfile.ConstantPool;
import classfile.GreateAttributeInfo;

/**
 * Created by yin on 18/4/17.
 */
public class CodeAttribute implements AttributeInfo {
    public ConstantPool cp;
    public int maxStack;
    public int maxLocals;
    public byte[] code;
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

    int getMaxStack() {
        return this.maxStack;
    }

    int getMaxLocals() {
        return this.maxLocals;
    }

    byte[] getCode() {
        return this.code;
    }

    ExceptionTableEntry[] getExceptionTable() {
        return this.exceptionTable;
    }


}

class ExceptionTableEntry {
    int startPc;
    int endPc;
    int handlerPc;
    int catchType;

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
