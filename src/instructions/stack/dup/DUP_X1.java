package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.Slot;

/*
bottom -> top
[...][c][b][a]
          __/
         |
         V
[...][c][a][b][a]
*/
public class DUP_X1 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
