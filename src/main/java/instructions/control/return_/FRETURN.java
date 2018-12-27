package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

public class FRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Thread_ thread = frame.getThread_();
        StackFrame_ currentFrame = thread.popStackFrame_();
        StackFrame_ invokerFrame = thread.getTopFrame();
        float retVal = currentFrame.getOperandStack().popFloat();
        invokerFrame.getOperandStack().pushFloat(retVal);
    }
}
