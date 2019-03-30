package classfile.attributeinfo.attributeinfos;

import classfile.attributeinfo.AttributeInfo;
import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/22.
 */
public class ConstantValueAttribute implements AttributeInfo {
    private int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }

    public int getConstantValueIndex() {
        return this.constantValueIndex;
    }
}
