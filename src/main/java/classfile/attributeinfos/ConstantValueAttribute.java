package classfile.attributeinfos;

import classfile.AttributeInfo;
import classfile.ClassReader;

/**
 * Created by yin on 18/4/22.
 */
public class ConstantValueAttribute implements AttributeInfo {
    int constantValueIndex;

    @Override
    public void readInfo(ClassReader reader) {
        this.constantValueIndex = reader.readUint16();
    }

    int getConstantValueIndex() {
        return this.constantValueIndex;
    }
}
