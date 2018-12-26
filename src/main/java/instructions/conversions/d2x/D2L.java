package instructions.conversions.d2x;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class D2L extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        double val = operandStack.popDouble();
        long updateVal = (long) val;
        operandStack.pushLong(updateVal);
    }
}
