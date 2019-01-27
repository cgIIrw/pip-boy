package instructions.stack.dup;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.stack.Slot_;

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
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Slot_ slot1 = stack.popSlot();
        Slot_ slot2 = stack.popSlot();
        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
        stack.pushSlot(slot1);
    }
}
