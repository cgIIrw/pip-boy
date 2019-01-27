package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.stack.Slot_;

// 复制栈顶的单个变量
public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Slot_ slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
