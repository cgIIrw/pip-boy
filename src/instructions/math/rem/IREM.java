package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class IREM extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        int val2 = stack.popInt();
        int val1 = stack.popInt();
        if (val2 == 0) {
            throw new ArithmeticException("java.lang.ArithmeticException: / by zero");
        }
        int res = val1 % val2;
        stack.pushInt(res);
    }
}
