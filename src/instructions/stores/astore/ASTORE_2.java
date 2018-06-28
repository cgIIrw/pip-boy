package instructions.stores.astore;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class ASTORE_2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        ASTORE.astore(frame, 2);
    }
}
