package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class FDIV extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        float d01 = stack.popFloat();
        float d02 = stack.popFloat();
        float result = d01 / d02;
        stack.pushDouble(result);
    }
}
