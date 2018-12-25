package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.Myobject;

public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        if (_acmp(frame)) {
            Branch.branch(frame, this.getOffset());
        }

    }

    public static boolean _acmp(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Myobject ref2 = stack.popRef();
        Myobject ref1 = stack.popRef();
        return ref1 == ref2;
    }
}
