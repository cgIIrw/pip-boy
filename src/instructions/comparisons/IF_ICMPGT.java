package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;

import static instructions.comparisons.IF_ICMPEQ._icmpPop;

public class IF_ICMPGT extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        int val1 = _icmpPop(frame)[0];
        int val2 = _icmpPop(frame)[1];

        if (val1 > val2) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
