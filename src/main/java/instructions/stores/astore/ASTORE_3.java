package instructions.stores.astore;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ASTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        ASTORE.astore(frame, 3);
    }
}
