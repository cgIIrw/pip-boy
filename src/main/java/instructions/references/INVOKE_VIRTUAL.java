package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.Class_;
import rtda.methodarea.rtcp.symref.MethodRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        Class_ currentClass = frame.getMethod_().getClass_();
        RuntimeConstantPool_ cp = currentClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
//        Method_ resolvedMethod = methodRef.resolvedMethod();

        if (methodRef.getName().equals("println")) {
            OperandStack_ stack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
                case "(Z)V":
                    System.out.println(stack.popInt() != 0);
                    break;
                case "(C)V":
                    System.out.println(stack.popInt());
                    break;
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    System.out.println(stack.popInt());
                    break;
                case "(F)V":
                    System.out.println(stack.popFloat());
                    break;
                case "(J)V":
                    System.out.println(stack.popLong());
                    break;
                case "(D)V":
                    System.out.println(stack.popDouble());
                    break;
                default:
                    throw new RuntimeException("println: " + methodRef.getDescriptor());
            }
            stack.popRef();


        }


//        if (resolvedMethod.isStatic()) {
//            throw new IncompatibleClassChangeError();
//        }
//        Instance_ ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

//        if (ref == null) {
//            // hack
//            if (methodRef.getName().equals("println")) {
//                _println(frame.getOperandStack(), methodRef.getDescriptor());
//                return;
//            }
//            throw new NullPointerException();
//        }
//
//        if (resolvedMethod.isProtected()
//                && resolvedMethod.getClass_().isSuperClassOf(currentClass)
//                && resolvedMethod.getClass_().getPackageName().equals(currentClass.getPackageName())
//                && ref.getClass_() != currentClass
//                && !ref.getClass_().isSubClassOf(currentClass)) {
//            throw new IllegalAccessError();
//        }
//
//        Method_ methodToBeInvoked = MethodRef.lookupMethodInClass(currentClass.getSuperClass(),
//                methodRef.getName(), methodRef.getDescriptor());
//
//        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
//            throw new AbstractMethodError();
//        }
//
//        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
//    }
//
//    public void _println(OperandStack_ stack, String descriptor) {

    }
}
