package instructions.loads.iload;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class ILOAD extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        iload(frame, index);
    }

    public static void iload(Myframe myframe, int index) {
        int val = myframe.getLocalVars().getInt(index);
        myframe.getOperandStack().pushInt(val);
    }
}
