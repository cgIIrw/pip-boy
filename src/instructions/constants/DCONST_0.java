package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class DCONST_0 extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushDouble(0.0);
    }
}
