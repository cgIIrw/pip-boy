package instructions.loads.aload;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ALOAD_2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ALOAD.aload(frame, 2);
    }
}
