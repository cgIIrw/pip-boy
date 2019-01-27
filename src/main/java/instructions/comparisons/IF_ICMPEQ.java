package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class IF_ICMPEQ extends BranchInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        int[] temp = _icmpPop(frame);
        int val1 = temp[0];
        int val2 = temp[1];

        if (val1 == val2) {
            Branch.branch(frame, this.getOffset());
        }
    }

    public static int[] _icmpPop(StackFrame_ stackFrame) {
        OperandStack_ stack = stackFrame.getOperandStack();
        int[] temp = new int[2];
        temp[1] = stack.popInt();
        temp[0] = stack.popInt();
        return temp;
    }
}
