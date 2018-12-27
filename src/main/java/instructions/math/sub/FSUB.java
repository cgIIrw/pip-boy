package instructions.math.sub;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class FSUB extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        float f01 = stack.popFloat();
        float f02 = stack.popFloat();
        float result = -f01 + f02;
        stack.pushFloat(result);
    }
}
