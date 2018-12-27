package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ICONST_5 extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().pushInt(5);
    }
}
