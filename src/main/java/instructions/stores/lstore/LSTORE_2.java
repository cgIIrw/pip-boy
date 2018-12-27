package instructions.stores.lstore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class LSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        LSTORE.lstore(frame, 2);
    }
}
