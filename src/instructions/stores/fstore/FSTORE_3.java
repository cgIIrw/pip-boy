package instructions.stores.fstore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class FSTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        FSTORE.fstore(frame, 3);
    }
}
