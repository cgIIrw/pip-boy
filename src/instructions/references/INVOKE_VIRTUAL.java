package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.*;

public class INVOKE_VIRTUAL extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        Myclass currentClass = frame.getMyMethod().getMyclass();
        RuntimeConstantPool cp = currentClass.getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef)(cp.getConstant(index).getVal());
//        MyMethod resolvedMethod = methodRef.resolvedMethod();

        if (methodRef.getName().equals("println")) {
            OperandStack stack = frame.getOperandStack();
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
//        Myobject ref = frame.getOperandStack().getRefFromTop(resolvedMethod.getArgSlotCount() - 1);

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
//                && resolvedMethod.getMyclass().isSuperClassOf(currentClass)
//                && resolvedMethod.getMyclass().getPackageName().equals(currentClass.getPackageName())
//                && ref.getMyclass() != currentClass
//                && !ref.getMyclass().isSubClassOf(currentClass)) {
//            throw new IllegalAccessError();
//        }
//
//        MyMethod methodToBeInvoked = MethodRef.lookupMethodInClass(currentClass.getSuperClass(),
//                methodRef.getName(), methodRef.getDescriptor());
//
//        if (methodToBeInvoked == null || methodToBeInvoked.isAbstract()) {
//            throw new AbstractMethodError();
//        }
//
//        MethodInvokeLogic.invokeMethod(frame, methodToBeInvoked);
//    }
//
//    public void _println(OperandStack stack, String descriptor) {

    }
}
