package instructions.stores.lstore;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        lstore(frame, index);

    }

    public static void lstore(Myframe myframe, int index) {
//        Myobject ref = myframe.getLocalVars().getRef(index);
        long ref = myframe.getOperandStack().popLong();
        myframe.getLocalVars().setLong(index, ref);
    }
}
