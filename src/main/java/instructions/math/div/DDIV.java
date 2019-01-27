package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class DDIV extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        double d01 = stack.popDouble();
        double d02 = stack.popDouble();
        double result = d01 / d02;
        stack.pushDouble(result);
    }
}
