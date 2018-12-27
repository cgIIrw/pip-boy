package instructions.loads.iload;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class ILOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        iload(frame, this.index);
    }

    public static void iload(StackFrame_ stackFrame, int index) {
        // 从getInt()可知推入栈顶的元素是从局部变量表复制而来
        int val = stackFrame.getLocalVars().getInt(index);
        stackFrame.getOperandStack().pushInt(val);
    }
}
