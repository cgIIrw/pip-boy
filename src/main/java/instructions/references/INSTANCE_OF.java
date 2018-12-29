package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.methodarea.Class_;
import rtda.heap.Instance_;

public class INSTANCE_OF extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Instance_ ref = stack.popRef();
        if (ref == null) {
            stack.pushInt(0);
        }

        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef)((cp.getConstant(index)).getVal());
        Class_ class_ = classRef.resolvedClass();
        // ref虽然是引用但是isInstanceOf调用的是内部的class进行判断
        if (ref.isInstanceOf(class_)) {
            stack.pushInt(1);
        } else {
            stack.pushInt(0);
        }
    }
}
