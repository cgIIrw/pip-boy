package instructions.stores.astore;

import instructions.base.Index8Instruction;
import rtda.Myframe;
import rtda.Myobject;

public class ASTORE extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        astore(frame, index);

    }

    public static void astore(Myframe myframe, int index) {
//        Myobject ref = myframe.getLocalVars().getRef(index);
        Myobject ref = myframe.getOperandStack().popRef();
        myframe.getLocalVars().setRef(index, ref);
    }
}
