package instructions.loads.lload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class LLOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        LLOAD.lload(frame, 3);
    }
}
