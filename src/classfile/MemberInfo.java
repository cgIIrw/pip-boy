package classfile;

import classfile.attributeinfos.CodeAttribute;

/**
 * Created by yin on 18/4/15.
 */
public class MemberInfo {
    ConstantPool cp;
    int accessFlags;
    int nameIndex;
    int descriptorIndex;
    AttributeInfo[] attributes;

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        int memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = new MemberInfo(reader, cp);
        }
        return members;
    }


    public MemberInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
        this.attributes = GreateAttributeInfo.readAttributes(reader, cp);
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    // 通过nameIndex来取出常量池中的相关数据,后面再实现
    public String getName() {
        return this.cp.getUtf8(this.nameIndex);
    }

    public String getDescriptor() {
        return this.cp.getUtf8(this.descriptorIndex);
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public CodeAttribute getCodeAttribute() {
        for (AttributeInfo info : getAttributes()) {
            String attrName = getName();
            switch (attrName) {
                case "Code":
                    return (CodeAttribute)info;
            }
        }
        return null;
    }

}
