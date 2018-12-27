package instructions.loads.iload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ILOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ILOAD.iload(frame, 0);
    }
}
