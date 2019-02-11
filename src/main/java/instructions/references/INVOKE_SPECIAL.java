package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.heap.Instance_;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;
import rtda.methodarea.rtcp.symref.MethodLookup;
import rtda.methodarea.rtcp.symref.MethodRef;
import rtda.stack.StackFrame_;

// 调用实例构造器方法、私有方法和父类方法
public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        InstanceKlass_ currentClass = frame.getMethod_().getInstanceKlass_();
        RuntimeConstantPool_ cp = currentClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
        InstanceKlass_ resolvedClass = methodRef.getInstanceKlass_();
        if (resolvedClass == null) {
            resolvedClass = ResolvedRef.resolvedClassRef(methodRef.getClassName(), methodRef.getRuntimeConstantPool());
        }
        Method_ resolvedMethod = methodRef.resolvedMethod();

        // 解析的方法是构造器则方法声明的类就是通过方法引用解析出的类，否则抛出错误
        if (resolvedMethod.getName().equals("<init>") && resolvedMethod.getInstanceKlass_() != resolvedClass) {
            throw new NoSuchMethodError("没有该方法！");
        }

        // 该方法如果是静态方法，抛出错误
        if (resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }

        // 被调用方法局部变量表第0号索引代表调用该方法的对象this，在未调用之前
        // 该对象位于外围方法的操作数栈中，通过索引找到该对象并引用，后续的访问
        // 判断会使用到该对象
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

        // JDK 1.0.2之后编译的类的这个标志必须为真 见《深入理解Java虚拟机》P173
        if (currentClass.isSuper()
                // 解析出来的类是父类
                && resolvedClass.isSuperClassOf(currentClass)
                // 解析的是父类的方法而且不是<init>构造器方法
                && !(resolvedMethod.getName().equals("<init>"))) {
            // 通过简单名和描述符找出父类满足条件的方法
            resolvedMethod = MethodLookup.lookupMethodInClass(currentClass.getSuperClass(),
                    methodRef.getName(), methodRef.getDescriptor());
        }

        // 该方法不能为空，不能是抽象方法
        if (resolvedMethod == null || resolvedMethod.isAbstract()) {
            throw new AbstractMethodError();
        }

        // 方法调用的公有逻辑
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
