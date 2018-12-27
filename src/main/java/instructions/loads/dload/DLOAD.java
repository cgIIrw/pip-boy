package instructions.loads.dload;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class DLOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        dload(frame, index);
    }

    public static void dload(StackFrame_ stackFrame, int index) {
        double val = stackFrame.getLocalVars().getDouble(index);
        stackFrame.getOperandStack().pushDouble(val);
    }
}
