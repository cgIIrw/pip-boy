package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class DDIV extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        double d01 = stack.popDouble();
        double d02 = stack.popDouble();
        double result = d01 / d02;
        stack.pushDouble(result);
    }
}
