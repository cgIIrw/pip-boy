package instructions.math.div;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class IDIV extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        int d01 = stack.popInt();
        int d02 = stack.popInt();
        int result = d01 / d02;
        stack.pushInt(result);
    }
}
