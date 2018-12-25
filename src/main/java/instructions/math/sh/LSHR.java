package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class LSHR extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        int i01 = stack.popInt();
        long i02 = stack.popLong();
        int s = i01 & 0x1f;
        long result = i02 >> s;
        stack.pushLong(result);
    }
}
