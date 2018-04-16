package classfile;

/**
 * Created by yin on 18/4/15.
 */
public class MemberInfo {
    ConstantPool cp;
    int accessFlags;
    int nameIndex;
    int descriptorIndex;
    AttributeInfo[] attributes;

    public MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        int memberCount = reader.readUint16();
        MemberInfo[] members = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            members[i] = readMember(reader, cp);
        }
        return members;
    }

    public MemberInfo readMember(ClassReader reader, ConstantPool cp) {
        MemberInfo mb = new MemberInfo();
        mb.cp = cp;
        mb.accessFlags = reader.readUint16();
        mb.nameIndex = reader.readUint16();
        mb.descriptorIndex = reader.readUint16();
        mb.attributes = readAttributes(reader, cp);
        return mb;
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
}
