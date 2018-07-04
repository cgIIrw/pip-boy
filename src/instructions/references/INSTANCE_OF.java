package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.ClassRef;
import rtda.heap.Myclass;
import rtda.heap.Myobject;
import rtda.heap.RuntimeConstantPool;

public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Myobject ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
        }

        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().runtimeConstantPool;
        ClassRef classRef = (ClassRef)((cp.getConstants(index)).getVal());
        Myclass myclass = classRef.resolvedClass();
        if (ref.isInstanceOf )
    }
}
