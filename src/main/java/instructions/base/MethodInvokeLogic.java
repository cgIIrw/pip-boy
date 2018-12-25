package instructions.base;

import rtda.Myframe;
import rtda.Mythread;
import rtda.Slot;
import rtda.heap.MyMethod;

public class MethodInvokeLogic {
    public static void invokeMethod(Myframe invokerFrame, MyMethod method) {

        // 获取调用方法的栈帧所在的线程
        Mythread mythread = invokerFrame.getMythread();

        // 通过栈帧所在线程和所属方法，创建一个新的栈帧
        Myframe newFrame = new Myframe(mythread, method);

        // 将创建的栈帧押入虚拟机栈顶
        mythread.pushMyframe(newFrame);

        // 下面的代码主要作用是进行参数传递，首先确定方法参数所占位置数
        int argSlotSlot = method.getArgSlotCount();

        if (argSlotSlot > 0) {
            for (int i = argSlotSlot - 1; i >= 0; i--) {

                // 方法参数首先是存储在调用方法的栈帧的操作数栈中
                Slot slot = invokerFrame.getOperandStack().popSlot();

                // 将弹出的参数存储在新创建的局部变量表中
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }
}
