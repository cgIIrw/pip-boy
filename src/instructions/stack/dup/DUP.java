package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.Slot;

public class DUP extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot = stack.popSlot();
        stack.pushSlot(slot);
        stack.pushSlot(slot);
    }
}
