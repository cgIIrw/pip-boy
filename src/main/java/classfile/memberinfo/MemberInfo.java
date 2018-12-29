package classfile.memberinfo;

import classfile.attributeinfo.AttributeInfo;
import classfile.attributeinfo.AttributeInfoFactory;
import classfile.attributeinfo.attributeinfos.CodeAttribute;
import classfile.constantpool.ConstantPool;
import classfile.utils.ClassReader;

/**
 * Created by yin on 18/4/15.
 */
// 相当于FieldInfo、MethodInfo
// 字段和方法由于结构相同用同一个结构体MemberInfo表示
// 这里的字段和方法可能是static的，也可以不是，所以并非
// 一定指类字段和类方法
public class MemberInfo {
    private ConstantPool cp;
    private int accessFlags;
    private int nameIndex;
    private int descriptorIndex;
    private AttributeInfo[] attributes;

    public static MemberInfo[] readMembers(ClassReader reader, ConstantPool cp) {
        int memberCount = reader.readUint16();
        MemberInfo[] memberInfos = new MemberInfo[memberCount];
        for (int i = 0; i < memberCount; i++) {
            memberInfos[i] = new MemberInfo(reader, cp);
        }
        return memberInfos;
    }

    private MemberInfo(ClassReader reader, ConstantPool cp) {
        this.cp = cp;
        this.accessFlags = reader.readUint16();
        this.nameIndex = reader.readUint16();
        this.descriptorIndex = reader.readUint16();
        this.attributes = AttributeInfoFactory.readAttributes(reader, cp);
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
            if (info instanceof CodeAttribute) {
                return (CodeAttribute) info;
            }
        }
        return null;
    }
}
