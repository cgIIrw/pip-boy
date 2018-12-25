package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushFloat(1.0f);
    }
}
