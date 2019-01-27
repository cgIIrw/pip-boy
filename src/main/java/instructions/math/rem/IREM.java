package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class IREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int val2 = stack.popInt();
        if (val2 == 0) {
            throw new ArithmeticException("被除数不能为0！");
        }
        int val1 = stack.popInt();
        int res = val1 % val2;
        stack.pushInt(res);
    }
}
