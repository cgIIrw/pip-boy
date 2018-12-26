package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;

public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        _dcmp(frame, true);
    }

    public static void _dcmp(StackFrame_ stackFrame, boolean gFlag) {
        OperandStack_ stack = stackFrame.getOperandStack();
        double v2 = stack.popDouble();
        double v1 = stack.popDouble();

        if (v1 > v2) {
            stack.pushInt(1);
        } else if (v1 == v2) {
            stack.pushInt(0);
        } else if (v1 < v2) {
            stack.pushInt(-1);
        } else if (gFlag) {
            stack.pushInt(1);
        } else {
            stack.pushInt(-1);
        }
    }
}
