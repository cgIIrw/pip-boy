package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class LCONST_0 extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().pushLong(0);
    }
}
