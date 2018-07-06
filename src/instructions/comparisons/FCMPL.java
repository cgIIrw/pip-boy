package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        FCMPG._fcmp(frame, false);
    }
}
