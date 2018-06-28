package instructions.loads.iload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ILOAD_3 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ILOAD.iload(frame, 3);
    }
}
