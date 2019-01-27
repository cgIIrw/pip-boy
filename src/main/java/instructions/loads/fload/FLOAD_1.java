package instructions.loads.fload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class FLOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        FLOAD.fload(frame, 1);
    }
}
