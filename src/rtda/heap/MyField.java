package rtda.heap;

import classfile.AttributeInfo;
import classfile.MemberInfo;
import classfile.attributeinfos.ConstantValueAttribute;

public class MyField extends ClassMember {
     //  《深入》178页，凡是index首先考虑的就是常量池中的位置
    int constValue_index;
    int slotId;

    public MyField(Myclass myclass, MemberInfo classFileMemberInfo) {
        super(myclass, classFileMemberInfo);
        copyAttributes(classFileMemberInfo);
    }

    public static MyField[] newFields(Myclass myclass, MemberInfo[] cfFields) {
        MyField[] fields = new MyField[cfFields.length];
        for (int i = 0; i < fields.length; i++) {
            // 参看构造函数
            fields[i] = new MyField(myclass, cfFields[i]);
        }
        return fields;
    }


    public void copyAttributes(MemberInfo classFileField) {

        ConstantValueAttribute constantValueAttribute = getConstantValueAttribute(classFileField.getAttributes());
        if (constantValueAttribute != null) {
            constValue_index = constantValueAttribute.getConstantValueIndex();
        }
    }


    // 添加一个可以得到ConstantValueAttribute的方法，这个属性代表final static
    public ConstantValueAttribute getConstantValueAttribute(AttributeInfo[] attributeInfos) {
        for (AttributeInfo info : attributeInfos) {
            if (info instanceof ConstantValueAttribute) {
                return (ConstantValueAttribute) info;
            }
        }
        return null;
    }


    public boolean isVolatile() {
        return 0 != (accessFlags & AccessFlags.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (accessFlags & AccessFlags.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (accessFlags & AccessFlags.ACC_ENUM);
    }

    // 描述符建《深入》long是J
    public boolean isLongOrDouble() {
        return getDescriptor().equals("J") || getDescriptor().equals("D");
    }
}
