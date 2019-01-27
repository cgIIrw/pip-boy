package instructions.math.and;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class LAND extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long l01 = stack.popLong();
        long l02 = stack.popLong();
        long result = l01 & l02;
        stack.pushLong(result);
    }
}
