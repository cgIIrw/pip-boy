package instructions.constants;


import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ACONST_NULL extends NoOperandsInstruction {

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushRef(null);
    }
}
