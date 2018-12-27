package instructions.references;

import instructions.base.Index16Instruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.heap.ClassRef;
import rtda.heap.Class_;
import rtda.heap.Instance_;
import rtda.heap.RuntimeConstantPool;

public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Instance_ ref = stack.popRef();
        // 这里push只是为了得到引用
        stack.pushRef(ref);
        if (ref == null) {
            return;
        }

        RuntimeConstantPool cp = frame.getMyMethod().getClass_().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef)((cp.getConstant(index)).getVal());
        Class_ class_ = classRef.resolvedClass();
        if (!ref.isInstanceOf(class_)) {
            throw new ClassCastException("java.lang.ClassCastException");
        }
    }
}
