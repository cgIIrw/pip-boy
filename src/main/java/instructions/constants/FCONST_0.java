package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FCONST_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushFloat(0.0f);
    }
}
