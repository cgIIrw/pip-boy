package instructions.math.rem;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class DREM extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        double val2 = stack.popDouble();
        double val1 = stack.popDouble();
        double res = val1 % val2;
        stack.pushDouble(res);
    }
}

