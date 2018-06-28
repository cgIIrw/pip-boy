package instructions.stores.lstore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class LSTORE_1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        LSTORE.lstore(frame, 1);
    }
}
