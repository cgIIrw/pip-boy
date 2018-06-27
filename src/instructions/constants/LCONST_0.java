package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class LCONST_0 extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushLong(0);
    }
}
