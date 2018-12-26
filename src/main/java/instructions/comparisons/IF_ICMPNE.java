package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;

import static instructions.comparisons.IF_ICMPEQ._icmpPop;

public class IF_ICMPNE extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        int val1 = _icmpPop(frame)[0];
        int val2 = _icmpPop(frame)[1];

        if (val1 != val2) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
