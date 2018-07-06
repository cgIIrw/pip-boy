package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.Mythread;

public class LRETURN extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        Mythread mythread = frame.getMythread();
        Myframe currentFrame = mythread.popMyframe();
        Myframe invokerFrame = mythread.getTopFrame();
        long retVal = currentFrame.getOperandStack().popLong();
        invokerFrame.getOperandStack().pushLong(retVal);
    }
}