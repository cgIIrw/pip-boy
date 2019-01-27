package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class LCMP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long v2 = stack.popLong();
        long v1 = stack.popLong();

        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else {
            stack.pushInt(-1);
        }
    }
}
