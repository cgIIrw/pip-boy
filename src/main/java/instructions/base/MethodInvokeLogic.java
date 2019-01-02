package instructions.base;

import rtda.stack.StackFrame_;
import rtda.stack.Thread_;
import rtda.stack.Slot_;
import rtda.methodarea.Method_;

// 方法调用指令调用方法的逻辑：给方法创建一个栈帧并推入
// Java虚拟机栈顶，然后传递参数
public class MethodInvokeLogic {
    public static void invokeMethod(StackFrame_ invokerFrame, Method_ method) {

        // 获取调用方法的栈帧所在的线程
        Thread_ thread = invokerFrame.getThread_();

        // 通过栈帧所在线程和所属方法，创建一个新的栈帧
        StackFrame_ newFrame = new StackFrame_(thread, method);

        // 将创建的栈帧押入虚拟机栈顶
        thread.pushStackFrame_(newFrame);

        // 下面的代码主要作用是进行参数传递，首先确定方法参数所占位置数
        int argSlotSlot = method.getArgSlotCount();

        if (argSlotSlot > 0) {
            for (int i = argSlotSlot - 1; i >= 0; i--) {

                // 方法参数首先是存储在调用方法的栈帧的操作数栈中
                Slot_ slot = invokerFrame.getOperandStack().popSlot();

                // 将弹出的参数存储在新创建的局部变量表中
                newFrame.getLocalVars().setSlot(i, slot);
            }
        }
    }
}
