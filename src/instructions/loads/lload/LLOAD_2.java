package instructions.loads.lload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class LLOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        LLOAD.lload(frame, 2);
    }
}
