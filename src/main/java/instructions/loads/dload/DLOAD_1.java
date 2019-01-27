package instructions.loads.dload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class DLOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        DLOAD.dload(frame, 1);
    }
}
