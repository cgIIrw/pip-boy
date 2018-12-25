package instructions.loads.dload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class DLOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        DLOAD.dload(frame, 0);
    }
}
