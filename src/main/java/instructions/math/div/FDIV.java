package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class FDIV extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        float d01 = stack.popFloat();
        float d02 = stack.popFloat();
        float result = d01 / d02;
        stack.pushDouble(result);
    }
}
