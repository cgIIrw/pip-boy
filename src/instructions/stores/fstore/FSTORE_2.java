package instructions.stores.fstore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FSTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        FSTORE.fstore(frame, 2);
    }
}
