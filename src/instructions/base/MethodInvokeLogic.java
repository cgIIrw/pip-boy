package instructions.base;

import rtda.Myframe;
import rtda.Mythread;
import rtda.Slot;
import rtda.heap.MyMethod;

public class MethodInvokeLogic {
    public static void invokeMethod(Myframe invokerFrame, MyMethod method) {
        // 创建新的frame，推入栈顶;
        Mythread mythread = invokerFrame.getMythread();
        Myframe newFrame = new Myframe(mythread, method);
        mythread.pushMyframe(newFrame);
        // 传递参数；
        int argSlotSlot = method.getArgSlotCount();
        if (argSlotSlot > 0) {
            for (int i = argSlotSlot - 1; i >= 0; i--) {
                Slot slot = invokerFrame.getOperandStack().popSlot();
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }


    }

}
