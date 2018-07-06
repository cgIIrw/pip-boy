package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.Mythread;

public class DRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        Mythread mythread = frame.getMythread();
        Myframe currentFrame = mythread.popMyframe();
        Myframe invokerFrame = mythread.getTopFrame();
        double retVal = currentFrame.getOperandStack().popDouble();
        invokerFrame.getOperandStack().pushDouble(retVal);
    }
}
