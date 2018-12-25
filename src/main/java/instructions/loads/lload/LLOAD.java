package instructions.loads.lload;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class LLOAD extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        lload(frame, index);
    }

    public static void lload(Myframe myframe, int index) {
        long val = myframe.getLocalVars().getLong(index);
        myframe.getOperandStack().pushLong(val);
    }
}
