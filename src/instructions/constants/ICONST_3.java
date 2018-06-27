package instructions.constants;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ICONST_3 extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushInt(3);
    }
}
