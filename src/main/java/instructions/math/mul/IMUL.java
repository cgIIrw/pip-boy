package instructions.math.mul;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class IMUL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int result = i01 * i02;
        stack.pushInt(result);
    }
}
