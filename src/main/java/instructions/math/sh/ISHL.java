package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

// int型左位移
public class ISHL extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();

        // 位移参数，也就是要位移的量
        int i01 = stack.popInt();
        int i02 = stack.popInt();

        // 位移量最大也就是32位，所以如果这个数过大也只是截取
        // 最后五位bit(相当于对32取余)，因为int是三十二位所以
        // 这里不用考虑无符号的情况
        int s = i01 & 0x1f;
        int result = i02 << s;
        stack.pushInt(result);
    }
}
