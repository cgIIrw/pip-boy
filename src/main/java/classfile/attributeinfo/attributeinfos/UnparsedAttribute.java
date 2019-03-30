package classfile.attributeinfo.attributeinfos;

import classfile.attributeinfo.AttributeInfo;
import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/17.
 */
public class UnparsedAttribute implements AttributeInfo {
    private String name;
    private long length;
    private byte[] info;

    public UnparsedAttribute(String name, long length) {
        this.name = name;
        this.length = length;
    }

    @Override
    public void readInfo(ClassReader reader) {
        this.info = reader.readBytes((int)(this.length));
    }

    byte[] Info() {
        return this.info;
    }
}
