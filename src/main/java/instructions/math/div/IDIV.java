package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class IDIV extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int d01 = stack.popInt();
        int d02 = stack.popInt();
        int result = d01 / d02;
        stack.pushInt(result);
    }
}
