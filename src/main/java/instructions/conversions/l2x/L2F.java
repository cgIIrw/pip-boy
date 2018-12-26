package instructions.conversions.l2x;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class L2F extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        long val = operandStack.popLong();
        float updateVal = (float) val;
        operandStack.pushFloat(updateVal);
    }
}
