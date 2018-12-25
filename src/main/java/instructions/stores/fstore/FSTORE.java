package instructions.stores.fstore;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        fstore(frame, index);

    }

    public static void fstore(Myframe myframe, int index) {
//        Myobject ref = myframe.getLocalVars().getRef(index);
        float ref = myframe.getOperandStack().popFloat();
        myframe.getLocalVars().setFloat(index, ref);
    }
}
