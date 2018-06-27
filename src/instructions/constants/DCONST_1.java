package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class DCONST_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushDouble(1.0);
    }
}
