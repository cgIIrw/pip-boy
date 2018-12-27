package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class DREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        double val2 = stack.popDouble();
        double val1 = stack.popDouble();

        // Java中支持浮点数取余
        double res = val1 % val2;
        stack.pushDouble(res);
    }
}

