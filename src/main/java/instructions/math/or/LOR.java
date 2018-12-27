package instructions.math.or;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class LOR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long i01 = stack.popLong();
        long i02 = stack.popLong();
        long result = i01 | i02;
        stack.pushLong(result);
    }
}
