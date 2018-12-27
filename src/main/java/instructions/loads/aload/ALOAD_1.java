package instructions.loads.aload;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ALOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ALOAD.aload(frame, 1);
    }
}
