package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class FCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        _fcmp(frame, true);

    }

    public static void _fcmp(Myframe myframe, boolean gFlag) {
        OperandStack stack = myframe.getOperandStack();
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
