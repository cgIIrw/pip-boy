package instructions.stores.dstore;

import instructions.base.Index8Instruction;
import rtda.stack.StackFrame_;

public class DSTORE extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        dstore(frame, index);
    }

    static void dstore(StackFrame_ stackFrame, int index) {
//        Instance_ ref = stackFrame.getLocalVars().getRef(index);
        double ref = stackFrame.getOperandStack().popDouble();
        stackFrame.getLocalVars().setDouble(index, ref);
    }
}
