package instructions.loads.fload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        FLOAD.fload(frame, 0);
    }
}
