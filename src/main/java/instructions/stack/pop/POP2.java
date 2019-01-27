package instructions.stack.pop;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

// 弹出两个操作数栈位置的变量
public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ oper = frame.getOperandStack();
        oper.popSlot();
        oper.popSlot();
    }
}
