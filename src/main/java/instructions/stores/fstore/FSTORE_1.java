package instructions.stores.fstore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class FSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        FSTORE.fstore(frame, 1);
    }
}
