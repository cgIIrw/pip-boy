package instructions.extended;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;
import rtda.heap.Instance_;

public class IFNONNULL extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Instance_ ref = frame.getOperandStack().popRef();

        if (ref != null) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
