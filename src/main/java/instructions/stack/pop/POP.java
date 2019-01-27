package instructions.stack.pop;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;

// 弹出占用一个操作数栈位置的变量
public class POP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().popSlot();
    }
}
