package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class FCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
