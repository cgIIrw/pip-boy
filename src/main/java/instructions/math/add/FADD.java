package instructions.math.add;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class FADD extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        float f01 = stack.popFloat();
        float f02 = stack.popFloat();
        float result = f01 + f02;
        stack.pushFloat(result);
    }
}
