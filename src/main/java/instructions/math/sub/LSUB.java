package instructions.math.sub;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class LSUB extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        long l01 = stack.popLong();
        long l02 = stack.popLong();
        long result = -l01 + l02;
        stack.pushLong(result);
    }
}
