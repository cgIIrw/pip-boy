package rtda.methodarea;

import classfile.attributeinfo.AttributeInfo;
import classfile.memberinfo.MemberInfo;
import classfile.attributeinfo.attributeinfos.ConstantValueAttribute;
import rtda.utils.AccessFlags;

public class Field_ extends ClassMember_ {

    private int constValue_index;
    private int slotId;

    // Field_构造方法，除了调用父类的构造方法，还会判断是否
    // 拥有常量值属性，如果有获得对应的常量池索引
    public Field_(Class_ class_, MemberInfo memberInfo) {
        super(class_, memberInfo);
        ConstantValueAttribute constantValueAttribute =
                getConstantValueAttribute(memberInfo.getAttributes());
        if (constantValueAttribute != null) {
            constValue_index = constantValueAttribute.getConstantValueIndex();
        }
    }

    // 创建多个字段，最终会遍历的调用Field_的构造方法
    public static Field_[] newFields(Class_ class_, MemberInfo[] memberInfos) {
        Field_[] fields = new Field_[memberInfos.length];
        for (int i = 0; i < fields.length; i++) {
            // 参看构造方法
            fields[i] = new Field_(class_, memberInfos[i]);
        }
        return fields;
    }

    // 添加一个可以得到ConstantValueAttribute的方法，这个属性代表final static
    private ConstantValueAttribute getConstantValueAttribute(AttributeInfo[] attributeInfos) {
        for (AttributeInfo info : attributeInfos) {
            if (info instanceof ConstantValueAttribute) {
                return (ConstantValueAttribute) info;
            }
        }
        return null;
    }

    public boolean isVolatile() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_VOLATILE);
    }

    public boolean isTransient() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_TRANSIENT);
    }

    public boolean isEnum() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_ENUM);
    }

    // 描述符见《深入》long是J
    public boolean isLongOrDouble() {
        return getDescriptor().equals("J") || getDescriptor().equals("D");
    }

    public int getSlotId() {
        return slotId;
    }

    public int getConstValue_index() {
        return constValue_index;
    }

    public void setConstValue_index(int constValue_index) {
        this.constValue_index = constValue_index;
    }

    public void setSlotId(int slotId) {
        this.slotId = slotId;
    }
}
