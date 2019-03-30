package classfile.attributeinfo;

import classfile.attributeinfo.AttributeInfo;
import classfile.attributeinfo.attributeinfos.*;
import classfile.constantpool.ConstantPool;
import classfile.utils.ClassReader;

/**
 * Created by cgrw on 18/4/17.
 */
// 属性表工厂
public class AttributeInfoFactory {

    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool cp) {
        int attributesCount = reader.readUint16();
        AttributeInfo[] attributes = new AttributeInfo[attributesCount];
        for (int i = 0; i < attributesCount; i++) {
            attributes[i] = readAttribute(reader, cp);
        }
        return attributes;
    }

    private static AttributeInfo readAttribute(ClassReader reader, ConstantPool cp) {
        int attrNameIndex = reader.readUint16();

        // 通过属性名索引在常量池中活的属性名，大小为u2
        String attrName = cp.getUtf8(attrNameIndex);

        // 获得属性长度(该长度不包括u2+u4)
        long attrLen = reader.readUint32();
        AttributeInfo attrInfo = newAttributeInfo(attrName, attrLen, cp);
        attrInfo.readInfo(reader);
        return attrInfo;
    }

    private static AttributeInfo newAttributeInfo(String attrName, long attrLen, ConstantPool cp) {
        switch (attrName) {
            case "Code":
                return new CodeAttribute(cp);
            case "ConstantValue":
                return new ConstantValueAttribute();
            case "Deprecated":
                return new DeprecatedAttribute();
            case "Exceptions":
                return new ExceptionsAttribute();
            case "LineNumberTable":
                return new LineNumberTableAttribute();
            case "LocalVariableTable":
                return new LocalVariableTableAttribute();
            case "SourceFile":
                return new SourceFileAttribute(cp);
            case "Synthetic":
                return new SyntheticAttribute();
            default:
                // 未解析的属性表直接跳过相应的字节长度，这个长度就是attrlen
                return new UnparsedAttribute(attrName, attrLen);
        }
    }
}