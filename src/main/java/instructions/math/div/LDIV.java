package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class LDIV extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long l01 = stack.popLong();
        long l02 = stack.popLong();
        long result = l01 / l02;
        stack.pushLong(result);
    }
}
