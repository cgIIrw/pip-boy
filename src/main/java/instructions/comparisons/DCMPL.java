package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class DCMPL extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {

        DCMPG._dcmp(frame, false);
    }
}
