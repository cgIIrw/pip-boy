package instructions.stack.pop;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class POP extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().popSlot();
    }
}
