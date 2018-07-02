package instructions.math.add;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class IADD extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int result = i01 + i02;
        stack.pushInt(result);
    }
}