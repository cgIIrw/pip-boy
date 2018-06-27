package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ICONST_2 extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushInt(2);
    }
}
