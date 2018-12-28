package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.MethodRef;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
        Method_ resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError();
        }
        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
