package instructions.constants;


import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

public class ACONST_NULL extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().pushRef(null);
    }
}
