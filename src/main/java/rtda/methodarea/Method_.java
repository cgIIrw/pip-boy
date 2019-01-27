package rtda.methodarea;

import classfile.attributeinfo.AttributeInfo;
import classfile.memberinfo.MemberInfo;
import classfile.attributeinfo.attributeinfos.CodeAttribute;
import rtda.methodarea.countparams_utils.CountParamsTool;
import rtda.utils.AccessFlags;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        // 判断方法是否是本地方法，如果是，那么求出代表返回值的描述符片段，
        // 通过该片段来判断返回字节码指令的类型
        if (this.isNative()) {
            // 获得方法的描述符
            String des = this.getDescriptor();
            Pattern pattern = Pattern.compile("(?=\\().*(?<=\\))");
            Matcher matcher = pattern.matcher(des);
            // 得到代表返回值的描述符片段
            String returnDes = matcher.replaceAll("");
            this.injectCodeAttribute(returnDes);
        }
    }

    // 注入字节码，为调用本地方法服务
    private void injectCodeAttribute(String returnType) {
        // 幸运数字7
        this.maxStack = 7;
        this.maxLocals = this.getArgSlotCount();
        // 第二个字节码指令用于执行本地方法后的返回
        switch (returnType.charAt(0)) {
            case 'V':
                this.code = new int[]{0xfe, 0xb1};
                break;
            case 'D':
                this.code = new int[]{0xfe, 0xaf};
                break;
            case 'F':
                this.code = new int[]{0xfe, 0xae};
                break;
            case 'J':
                this.code = new int[]{0xfe, 0xad};
                break;
            case 'L':
            case '[':
                this.code = new int[]{0xfe, 0xb0};
                break;
            default:
                this.code = new int[]{0xfe, 0xac};
                break;
        }
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

    //
//    public void calcArgSlotCount() {
//        MethodDescriptorParser p = new MethodDescriptorParser();
//        MethodDescriptor parsedDescriptor = p.parseMethodDescriptor(this.getDescriptor());
//
//        for (String paramType : parsedDescriptor.getParameterTypes()) {
//            this.argSlotCount++;
//            if (paramType.equals("J") || paramType.equals("D")) {
//                this.argSlotCount++;
//            }
//        }
//
//        if (!this.isStatic()) {
//            this.argSlotCount++;
//        }
//    }
    private void calcArgSlotCount() {
        this.argSlotCount = CountParamsTool.countMethod(this.getDescriptor());
        if (!this.isStatic()) {
            this.argSlotCount++;
        }
    }
}
