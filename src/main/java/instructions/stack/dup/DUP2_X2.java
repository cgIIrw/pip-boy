package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.stack.Slot_;

/*
bottom -> top
[...][d][c][b][a]
       ____/ __/
      |   __/
      V  V
[...][b][a][d][c][b][a]
*/
public class DUP2_X2 extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Slot_ slot1 = stack.popSlot();
        Slot_ slot2 = stack.popSlot();
        Slot_ slot3 = stack.popSlot();
        Slot_ slot4 = stack.popSlot();

        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot4);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
