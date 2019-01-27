package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

// float值进行比较，无法比较时因为传入的参数是true，所以返回1
public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        _fcmp(frame, true);
    }

    public static void _fcmp(StackFrame_ stackFrame, boolean gFlag) {
        OperandStack_ stack = stackFrame.getOperandStack();
        float v2 = stack.popFloat();
        float v1 = stack.popFloat();

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
