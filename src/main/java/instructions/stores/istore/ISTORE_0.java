package instructions.stores.istore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ISTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ISTORE.istore(frame, 0);
    }
}
