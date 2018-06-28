package instructions.loads.fload;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class FLOAD extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        fload(frame, index);
    }

    public static void fload(Myframe myframe, int index) {
        float val = myframe.getLocalVars().getFloat(index);
        myframe.getOperandStack().pushFloat(val);
    }
}
