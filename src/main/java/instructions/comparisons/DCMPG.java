package instructions.comparisons;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class DCMPG extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        _dcmp(frame, true);

    }

    public static void _dcmp(Myframe myframe, boolean gFlag) {
        OperandStack stack = myframe.getOperandStack();
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
