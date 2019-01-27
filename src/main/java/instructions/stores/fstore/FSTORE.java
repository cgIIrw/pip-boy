package instructions.stores.fstore;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class FSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        fstore(frame, index);
    }

    static void fstore(StackFrame_ stackFrame, int index) {
//        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        float ref = stackFrame.getOperandStack().popFloat();
        stackFrame.getLocalVars().setFloat(index, ref);
    }
}
