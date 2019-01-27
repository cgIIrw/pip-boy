package instructions.math.neg;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class LNEG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long val = stack.popLong();
        stack.pushLong(-val);
    }
}
