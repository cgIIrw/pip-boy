package instructions.extended;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;
import rtda.heap.Myobject;

public class IFNULL extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        Myobject ref = frame.getOperandStack().popRef();

        if (ref == null) {
            Branch.branch(frame, this.getOffset());
        }
    }
}
