package rtda.methodarea;

import classfile.attributeinfo.AttributeInfo;
import classfile.memberinfo.MemberInfo;
import classfile.attributeinfo.attributeinfos.CodeAttribute;
import rtda.utils.AccessFlags;
import rtda.heap.MethodDescriptor;
import rtda.heap.MethodDescriptorParser;

public class Method_ extends ClassMember_ {
    private int maxStack;
    private int maxLocals;
    private int[] code;
    private int argSlotCount;

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public Method_(Class_ class_, MemberInfo memberInfo) {
        super(class_, memberInfo);
        CodeAttribute codeAttribute = getCodeAttribute(memberInfo.getAttributes());
        if (codeAttribute != null) {
            this.maxStack = codeAttribute.getMaxStack();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.code = codeAttribute.getCode();
        }
        calcArgSlotCount();
    }

    // 创建多个method，最终会遍历的调用Method_的构造方法
    public static Method_[] newMethods(Class_ class_, MemberInfo[] memberInfos) {
        Method_[] methods = new Method_[memberInfos.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new Method_(class_, memberInfos[i]);
        }
        return methods;
    }

    private CodeAttribute getCodeAttribute(AttributeInfo[] attributeInfos) {
        for (AttributeInfo info : attributeInfos) {
            if (info instanceof CodeAttribute) {
                return (CodeAttribute) info;
            }
        }
        return null;
    }


    public boolean isSynchronized() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_SYNCHRONIZED);
    }

    public boolean isBridge() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_BRIDGE);
    }

    public boolean isVarargs() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_VARARGS);
    }

    public boolean isNative() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_NATIVE);
    }

    public boolean isAbstract() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_ABSTRACT);
    }

    public boolean isStrict() {
        return 0 != (getAccessFlags() & AccessFlags.ACC_STRICT);
    }

    public int getArgSlotCount() {
        return argSlotCount;
    }

    public int[] getCode() {
        return code;
    }

    public void calcArgSlotCount() {
        MethodDescriptorParser p = new MethodDescriptorParser();
        MethodDescriptor parsedDescriptor = p.parseMethodDescriptor(this.getDescriptor());

        for (String paramType : parsedDescriptor.getParameterTypes()) {
            this.argSlotCount++;
            if (paramType.equals("J") || paramType.equals("D")) {
                this.argSlotCount++;
            }
        }

        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }
}
