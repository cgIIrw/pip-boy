package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        DCMPG._dcmp(frame, false);
    }
}
