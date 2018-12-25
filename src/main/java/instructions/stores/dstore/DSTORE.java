package instructions.stores.dstore;

import instructions.base.Index8Instruction;
import rtda.Myframe;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        dstore(frame, index);

    }

    public static void dstore(Myframe myframe, int index) {
//        Myobject ref = myframe.getLocalVars().getRef(index);
        double ref = myframe.getOperandStack().popDouble();
        myframe.getLocalVars().setDouble(index, ref);
    }
}
