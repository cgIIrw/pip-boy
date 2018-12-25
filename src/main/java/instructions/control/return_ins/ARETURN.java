package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.Mythread;
import rtda.heap.Myobject;

public class ARETURN extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        Mythread mythread = frame.getMythread();
        Myframe currentFrame = mythread.popMyframe();
        Myframe invokerFrame = mythread.getTopFrame();
        Myobject retVal = currentFrame.getOperandStack().popRef();
        invokerFrame.getOperandStack().pushRef(retVal);
    }
}
