package instructions.loads.aload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ALOAD_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ALOAD.aload(frame, 0);
    }
}
