package instructions.math.or;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class LOR extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        long i01 = stack.popLong();
        long i02 = stack.popLong();
        long result = i01 | i02;
        stack.pushLong(result);
    }
}
