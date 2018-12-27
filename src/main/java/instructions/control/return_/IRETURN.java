package instructions.control.return_;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

public class IRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Thread_ thread = frame.getThread_();
        StackFrame_ currentFrame = thread.popStackFrame_();
        StackFrame_ invokerFrame = thread.getTopFrame();
        int retVal = currentFrame.getOperandStack().popInt();
        invokerFrame.getOperandStack().pushInt(retVal);
    }
}
