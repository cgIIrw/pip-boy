package instructions.stores.istore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ISTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ISTORE.istore(frame, 2);
    }
}
