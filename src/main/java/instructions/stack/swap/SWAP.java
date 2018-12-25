package instructions.stack.swap;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.Slot;

/*
bottom -> top
[...][c][b][a]
          \/
          /\
         V  V
[...][c][a][b]
*/
public class SWAP extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();

        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
    }
}
