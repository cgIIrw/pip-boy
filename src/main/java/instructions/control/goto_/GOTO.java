package instructions.control.goto_ins;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Branch.branch(frame, this.getOffset());
    }
}
