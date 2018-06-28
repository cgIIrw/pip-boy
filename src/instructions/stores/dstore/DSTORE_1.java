package instructions.stores.dstore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class DSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        DSTORE.dstore(frame, 1);
    }
}
