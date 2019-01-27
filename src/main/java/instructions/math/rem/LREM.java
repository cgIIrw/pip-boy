package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class LREM extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        long val2 = stack.popLong();
        if (val2 == 0) {
            throw new ArithmeticException("被除数不能为0！");
        }
        long val1 = stack.popLong();
        long res = val1 % val2;
        stack.pushLong(res);
    }
}
