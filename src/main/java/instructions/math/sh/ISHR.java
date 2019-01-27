package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

// int型算数右位移
public class ISHR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int i01 = stack.popInt();
        int i02 = stack.popInt();
        int s = i01 & 0x1f;
        int result = i02 >> s;
        stack.pushInt(result);
    }
}
