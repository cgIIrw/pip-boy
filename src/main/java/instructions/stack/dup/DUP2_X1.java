package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.stack.Slot_;

/*
bottom -> top
[...][c][b][a]
       _/ __/
      |  |
      V  V
[...][b][a][c][b][a]
*/
public class DUP2_X1 extends NoOperandsInstruction {

    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Slot_ slot1 = stack.popSlot(); // a
        Slot_ slot2 = stack.popSlot(); // b
        Slot_ slot3 = stack.popSlot(); // c

        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
        stack.pushSlot(slot3);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
