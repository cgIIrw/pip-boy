package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.InstanceKlass_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.heap.Instance_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

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

        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef)((cp.getConstant(index)).getVal());
        InstanceKlass_ instanceKlass_ = classRef.resolvedClass();
        if (!ref.isInstanceOf(instanceKlass_)) {
            throw new ClassCastException("java.lang.ClassCastException");
        }
    }
}
