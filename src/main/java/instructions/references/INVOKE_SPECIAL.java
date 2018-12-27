package instructions.references;

import instructions.base.Index16Instruction;
import rtda.stack.StackFrame_;

public class INVOKE_SPECIAL extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
//        Class_ currentClass = frame.getMyMethod().getClass_();
//        RuntimeConstantPool cp = currentClass.getRuntimeConstantPool();
//        MethodRef methodRef = (MethodRef)(cp.getConstant(index).getVal());
//        Class_ resolvedClass = methodRef.resolvedClass();
//        MyMethod resolvedMethod = methodRef.resolvedMethod();
//
//        if (resolvedMethod.getName().equals("<init>") && resolvedMethod.getClass_() != resolvedClass) {
//            throw new NoSuchMethodError();
//        }
//
//        if (resolvedMethod.isStatic()) {
//            throw new IncompatibleClassChangeError();
//        }
//
//        Instance_ ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount());
//        if (ref == null) {
//            throw new NullPointerException();
//        }
//
//        if (resolvedMethod.isProtected()
//                && resolvedMethod.getClass_().isSuperClassOf(currentClass)
//                && resolvedMethod.getClass_().getPackageName().equals(currentClass.getPackageName())
//                && (ref.getClass_() != currentClass)
//                && ref.getClass_().isSubClassOf(currentClass)) {
//            throw new IllegalAccessError();
//        }
//        MyMethod methodToBeInvoked = resolvedMethod;
//        if (currentClass.isSuper()
//                && resolvedClass.isSubClassOf(currentClass)
//                && resolvedMethod.getName().equals("<init>")) {
//            methodToBeInvoked = MethodRef.lookupMethodInClass(currentClass.getSuperClass(),
//                    methodRef.getName(), methodRef.getDescriptor());
//        }
//
//        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
//            throw new AbstractMethodError();
//        }
//
//        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
        frame.getOperandStack().popRef();
    }
}
