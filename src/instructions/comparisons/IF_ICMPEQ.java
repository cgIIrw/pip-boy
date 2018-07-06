package instructions.comparisons;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class IF_ICMPEQ extends BranchInstruction {
    @Override
    public void execute(Myframe frame) {
        int val1 = _icmpPop(frame)[0];
        int val2 = _icmpPop(frame)[1];

        if (val1 == val2) {
            Branch.branch(frame, this.getOffset());
        }

    }

    public static int[] _icmpPop(Myframe myframe) {
        OperandStack stack = myframe.getOperandStack();
        int[] temp = new int[2];
        temp[1] = stack.popInt();
        temp[0] = stack.popInt();
        return temp;
    }
}
