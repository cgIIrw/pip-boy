package instructions.stores.astore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ASTORE_3 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ASTORE.astore(frame, 3);
    }
}
