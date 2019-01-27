package instructions.stores.astore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ASTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ASTORE.astore(frame, 2);
    }
}
