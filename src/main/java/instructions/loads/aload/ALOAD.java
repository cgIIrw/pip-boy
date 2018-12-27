package instructions.loads.aload;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;
import rtda.heap.Instance_;

public class ALOAD extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        aload(frame, index);

    }

    public static void aload(StackFrame_ stackFrame, int index) {
        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        stackFrame.getOperandStack().pushRef(ref);
    }
}
