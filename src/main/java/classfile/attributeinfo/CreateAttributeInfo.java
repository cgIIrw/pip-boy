package classfile;

import classfile.attributeinfos.*;
import classfile.utils.ClassReader;

/**
 * Created by yin on 18/4/17.
 */
public class CreateAttributeInfo {

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
        String attrName = cp.getUtf8(attrNameIndex);
        long attrLen = reader.readUint32();
        // 属性表是通过常量中的常量来索引
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
                return new UnparsedAttribute(attrName, attrLen);
        }
    }

}