package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.heap.Instance_;

public class IF_ACMPEQ extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        if (_acmp(frame)) {
            Branch.branch(frame, this.getOffset());
        }
    }

    public static boolean _acmp(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Instance_ ref2 = stack.popRef();
        Instance_ ref1 = stack.popRef();
        return ref1 == ref2;
    }
}
