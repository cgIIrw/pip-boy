package instructions.control.goto_ins;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;

public class GOTO extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        Branch.branch(frame, this.getOffset());
    }
}
