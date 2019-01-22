package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;

import static instructions.comparisons.IF_ICMPEQ._icmpPop;

public class IF_ICMPLT extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        int[] temp = _icmpPop(frame);
        int val1 = temp[0];
        int val2 = temp[1];

        if (val1 < val2) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
