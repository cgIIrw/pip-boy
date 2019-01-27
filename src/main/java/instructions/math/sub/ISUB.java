package instructions.math.sub;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class ISUB extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int result = -i01 + i02;
        stack.pushInt(result);
    }
}
