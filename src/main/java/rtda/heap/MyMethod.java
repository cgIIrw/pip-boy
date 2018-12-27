package rtda.heap;

import classfile.attributeinfo.AttributeInfo;
import classfile.memberinfo.MemberInfo;
import classfile.attributeinfo.attributeinfos.CodeAttribute;

public class MyMethod extends ClassMember {
    int maxStack;
    int maxLocals;
    int[] code;
    int argSlotCount;

    public int getMaxStack() {
        return maxStack;
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public MyMethod(Class_ class_, MemberInfo classFileMemberInfo) {
        super(class_, classFileMemberInfo);
        copyAttributes(classFileMemberInfo);
        calcArgSlotCount();
    }

    public static MyMethod[] newMethods(Class_ class_, MemberInfo[] cfMethods) {
        MyMethod[] methods = new MyMethod[cfMethods.length];
        for (int i = 0; i < methods.length; i++) {
            methods[i] = new MyMethod(class_, cfMethods[i]);
        }
        return methods;
    }

    public void copyAttributes(MemberInfo cfMethod) {
        CodeAttribute codeAttribute = getConstantValueAttribute(cfMethod.getAttributes());
        if (codeAttribute != null) {
            this.maxStack = codeAttribute.getMaxStack();
            this.maxLocals = codeAttribute.getMaxLocals();
            this.code = codeAttribute.getCode();
//            int[] tempi = new int[temp.length];
//
//            for (int i = 0; i < temp.length; i++) {
//               tempi[i] = temp[i] & 0xff;
//            }
//            this.code = tempi;
        }
    }

    public CodeAttribute getConstantValueAttribute(AttributeInfo[] attributeInfos) {
        for (AttributeInfo info : attributeInfos) {
            if (info instanceof CodeAttribute) {
                return (CodeAttribute)info;
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
