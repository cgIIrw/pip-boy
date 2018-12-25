package instructions.stores.istore;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class ISTORE extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        istore(frame, index);

    }

    public static void istore(Myframe myframe, int index) {
//        Myobject ref = myframe.getLocalVars().getRef(index);
        int ref = myframe.getOperandStack().popInt();
        myframe.getLocalVars().setInt(index, ref);
    }
}
