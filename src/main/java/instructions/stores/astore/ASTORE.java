package instructions.stores.astore;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;
import rtda.heap.Instance_;

public class ASTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        astore(frame, index);
    }

    static void astore(StackFrame_ stackFrame, int index) {
//        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        Instance_ ref = stackFrame.getOperandStack().popRef();
        stackFrame.getLocalVars().setRef(index, ref);
    }
}
