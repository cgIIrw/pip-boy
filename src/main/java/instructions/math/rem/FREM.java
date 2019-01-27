package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class FREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        float val2 = stack.popFloat();
        float val1 = stack.popFloat();
        float res = val1 % val2;
        stack.pushFloat(res);
    }
}
