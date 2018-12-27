package instructions.stores.istore;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class ISTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        istore(frame, index);
    }

    static void istore(StackFrame_ stackFrame, int index) {
//        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        int ref = stackFrame.getOperandStack().popInt();
        stackFrame.getLocalVars().setInt(index, ref);
    }
}
