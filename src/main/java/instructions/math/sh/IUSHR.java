package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

// int型逻辑右位移
public class IUSHR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int s = i01 & 0x1f;

        // 逻辑右位移这里为了简便直接采用Java的逻辑
        // 右位移，不采用实现起来也不复杂
        int result = i02 >>> s;
        stack.pushInt(result);
    }
}
