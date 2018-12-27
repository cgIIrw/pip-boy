package instructions.control.return_;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;
import rtda.heap.Instance_;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Thread_ thread = frame.getThread_();
        StackFrame_ currentFrame = thread.popStackFrame_();
        StackFrame_ invokerFrame = thread.getTopFrame();
        Instance_ retVal = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
