package instructions.conversions.i2x;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class I2B extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        int val = operandStack.popInt();
        byte updateVal = (byte) val;
        operandStack.pushInt(updateVal);
    }
}
