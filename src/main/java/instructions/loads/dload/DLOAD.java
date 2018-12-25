package instructions.loads.dload;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        dload(frame, index);
    }

    public static void dload(Myframe myframe, int index) {
        double val = myframe.getLocalVars().getDouble(index);
        myframe.getOperandStack().pushDouble(val);
    }
}
