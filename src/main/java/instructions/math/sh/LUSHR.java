package instructions.math.sh;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

// long型逻辑右位移
public class LUSHR extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        int i01 = stack.popInt();
        long i02 = stack.popLong();
        int s = i01 & 0x3f;
        long result = i02 >>> s;
        stack.pushLong(result);
    }
}
