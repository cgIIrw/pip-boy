package instructions.stores.dstore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class DSTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        DSTORE.dstore(frame, 0);
    }
}
