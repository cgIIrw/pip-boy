package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.heap.Instance_;
import rtda.heap.StringPool;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.symref.MethodLookup;
import rtda.methodarea.rtcp.symref.MethodRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
// 调用方法：当前方法；被调用方法：当前方法内调用的方法；
public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        InstanceKlass_ currentClass = frame.getMethod_().getInstanceKlass_();
        RuntimeConstantPool_ cp = currentClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
        // todo 钩子方法，后续应该被替换 ---------------------------------------
        // 当执行*.println方法时，未真正进入该方法，而是在当前frame模拟执行println方法
        if (methodRef.getName().equals("println")) {
            println_(frame.getOperandStack(), methodRef.getDescriptor());
            // 钩子方法不仅会让操作数栈弹出打印的参数(上一步已完成)，还需要弹出调用方法
            // 的引用，因为这里的frame是调用方法所属的frame，而调用方法在调用被调用方
            // 法时需要将引用传入被调用方法的局部变量表
            frame.getOperandStack().popRef();
            return;
        }
        // todo -------------------------------------------------------------
        Method_ resolvedMethod = methodRef.resolvedMethod();

        if (resolvedMethod == null || resolvedMethod.isAbstract()) {
            throw new AbstractMethodError();
        }

        // 该方法如果是静态方法，抛出错误
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        // 被调用方法局部变量表第0号索引代表this，是从调用该方法的帧的操作数栈倒入的
        Instance_ ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount());
        if (ref == null) {
            throw new NullPointerException();
        }

        // 一个方法是protected的，并且不满足后面括号内的条件
        if (resolvedMethod.isProtected() && !(
                // 被调用方法的类是当前类的父类
                resolvedMethod.getInstanceKlass_().isSuperClassOf(currentClass)
                        // 被调用方法的类的包名等于当前类的包名
                        || resolvedMethod.getInstanceKlass_().getPackageName().equals(currentClass.getPackageName())
                        // 被调用方法的类等于当前类
                        || (ref.getInstanceKlass_() == currentClass)
                        // 被调用当前的类是当前类的子类
                        || ref.getInstanceKlass_().isSubClassOf(currentClass))) {
            // 抛出非法访问权限的错误
            throw new IllegalAccessError();
        }

        // 和INVOKE_SPECIAL的主要区别
        resolvedMethod = MethodLookup.lookupMethodInClass(ref.getInstanceKlass_(),
                methodRef.getName(), methodRef.getDescriptor());
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }

    // todo 钩子函数 ------------------
    private void println_(OperandStack_ operandStack, String descriptor) {
        switch (descriptor) {
            case "(Z)V":
                System.out.println(operandStack.popInt() != 0);
            case "(C)V":
            case "(B)V":
            case "(S)V":
            case "(I)V":
                System.out.println(operandStack.popInt());
                break;
            case "(F)V":
                System.out.println(operandStack.popFloat());
                break;
            case "(J)V":
                System.out.println(operandStack.popLong());
                break;
            case "(D)V":
                System.out.println(operandStack.popDouble());
                break;
            case "(Ljava/lang/String;)V":
                Instance_ jStr = operandStack.popRef();
                System.out.println(StringPool.goString(jStr));
                break;
            default:
                operandStack.popRef();
        }
    }
}
