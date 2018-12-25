package instructions.loads.aload;

import instructions.base.Index8Instruction;
import rtda.Myframe;
import rtda.heap.Myobject;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        aload(frame, index);

    }

    public static void aload(Myframe myframe, int index) {
        Myobject ref = myframe.getLocalVars().getRef(index);
        myframe.getOperandStack().pushRef(ref);
    }
}
