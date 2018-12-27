package instructions.loads.iload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ILOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ILOAD.iload(frame, 3);
    }
}
