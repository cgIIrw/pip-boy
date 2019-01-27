package instructions.loads.lload;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class LLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        lload(frame, index);
    }

    public static void lload(StackFrame_ stackFrame, int index) {
        long val = stackFrame.getLocalVars().getLong(index);
        stackFrame.getOperandStack().pushLong(val);
    }
}
