package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        FCMPG._fcmp(frame, false);
    }
}
