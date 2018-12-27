package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        frame.getThread_().popStackFrame_();
    }
}
