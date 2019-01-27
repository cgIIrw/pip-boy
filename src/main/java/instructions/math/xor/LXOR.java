package instructions.math.xor;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class LXOR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long i01 = stack.popLong();
        long i02 = stack.popLong();
        long result = i01 ^ i02;
        stack.pushLong(result);
    }
}
