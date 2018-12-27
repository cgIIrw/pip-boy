package instructions.math.add;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class DADD extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        double d01 = stack.popDouble();
        double d02 = stack.popDouble();
        double result = d01 + d02;
        stack.pushDouble(result);
    }
}
