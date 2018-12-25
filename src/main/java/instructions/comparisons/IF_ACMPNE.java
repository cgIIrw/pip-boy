package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;

import static instructions.comparisons.IF_ACMPEQ._acmp;

public class IF_ACMPNE extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        if (!_acmp(frame)) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
