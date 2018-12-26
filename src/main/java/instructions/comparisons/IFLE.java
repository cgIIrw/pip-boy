package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;

public class IFLE extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {

        int val = frame.getOperandStack().popInt();

        if (val <= 0) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
