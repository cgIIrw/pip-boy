package instructions.stores.lstore;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class LSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        lstore(frame, index);
    }

    static void lstore(StackFrame_ stackFrame, int index) {
//        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        long ref = stackFrame.getOperandStack().popLong();
        stackFrame.getLocalVars().setLong(index, ref);
    }
}
