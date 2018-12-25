package instructions.loads.aload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ALOAD_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ALOAD.aload(frame, 1);
    }
}
