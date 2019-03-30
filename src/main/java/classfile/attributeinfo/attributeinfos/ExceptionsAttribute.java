package classfile.attributeinfo.attributeinfos;

import classfile.attributeinfo.AttributeInfo;
import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/22.
 */
public class ExceptionsAttribute implements AttributeInfo {
    private int[] exceptionIndexTable;

    @Override
    public void readInfo(ClassReader reader) {
        this.exceptionIndexTable = reader.readUint16s();
    }

    public int[] getExceptionIndexTable() {
        return this.exceptionIndexTable;
    }
}
