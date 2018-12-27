package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.stack.StackFrame_;
import rtda.heap.MethodRef;
import rtda.heap.MyMethod;
import rtda.heap.RuntimeConstantPool;

public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getClass_().getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
        MyMethod resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
