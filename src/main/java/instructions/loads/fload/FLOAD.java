package instructions.loads.fload;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class FLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        fload(frame, index);
    }

    public static void fload(StackFrame_ stackFrame, int index) {
        float val = stackFrame.getLocalVars().getFloat(index);
        stackFrame.getOperandStack().pushFloat(val);
    }
}
