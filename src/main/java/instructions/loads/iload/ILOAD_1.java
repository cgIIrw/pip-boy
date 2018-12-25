package instructions.loads.iload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ILOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ILOAD.iload(frame, 1);
    }
}
