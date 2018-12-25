package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class ISHR extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int s = i01 & 0x1f;
        int result = i02 >> s;
        stack.pushInt(result);
    }
}
