package instructions.stores.astore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ASTORE_0 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ASTORE.astore(frame, 0);
    }
}
