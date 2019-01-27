package classfile.attributeinfo.attributeinfos;

import classfile.attributeinfo.AttributeInfo;
import classfile.utils.ClassReader;
import classfile.constantpool.ConstantPool;
import classfile.attributeinfo.AttributeInfoFactory;

/**
 * Created by yin on 18/4/17.
 */
public class CodeAttribute implements AttributeInfo {
    private ConstantPool cp;
    private int maxStack;
    private int maxLocals;
    private int[] code;
    private ExceptionTableEntry[] exceptionTable;
    private AttributeInfo[] attributes;

    public CodeAttribute(ConstantPool cp) {
        this.cp = cp;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.maxStack = reader.readUint16();
        this.maxLocals = reader.readUint16();
        // 虽然大小是u4，但是codeLength在java虚拟机规范中限制了一个方
        // 法不允许超过65535条字节码指令，所以实际只使用u2，于是在这里将
        // long转化为int
        int codeLength = (int) (reader.readUint32());
        byte[] temp = reader.readBytes(codeLength);
        // 由于是八位无符号字节，所以这里还需要将其转换为一个int数组存储
        int[] temp2 = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            temp2[i] = temp[i] & 0xff;
        }
        this.code = temp2;
        this.exceptionTable = ExceptionTableEntry.readExceptionTable(reader);
        this.attributes = AttributeInfoFactory.readAttributes(reader, this.cp);
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public int[] getCode() {
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
