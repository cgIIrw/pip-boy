package instructions.conversions.f2x;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class F2L extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        float val = operandStack.popFloat();
        long updateVal = (long) val;
        operandStack.pushLong(updateVal);
    }
}
