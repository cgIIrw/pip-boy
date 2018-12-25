package instructions.stores.fstore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FSTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        FSTORE.fstore(frame, 0);
    }
}
