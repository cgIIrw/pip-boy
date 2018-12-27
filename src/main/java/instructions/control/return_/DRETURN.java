package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Thread_ thread = frame.getThread_();
        StackFrame_ currentFrame = thread.popStackFrame_();
        StackFrame_ invokerFrame = thread.getTopFrame();
        double retVal = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(retVal);
    }
}
