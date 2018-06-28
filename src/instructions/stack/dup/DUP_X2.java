package instructions.stack.dup;

/*
bottom -> top
[...][c][b][a]
       _____/
      |
      V
[...][a][c][b][a]
*/
import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.Slot;

public class DUP_X2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Slot slot1 = stack.popSlot();
        Slot slot2 = stack.popSlot();
        Slot slot3 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
