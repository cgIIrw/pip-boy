package instructions.stack.swap;

import instructions.base.NoOperandsInstruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.stack.Slot_;

/*
交换栈顶的两个变量的位置，如图所示：
bottom -> top
[...][c][b][a]
          \/
          /\
         V  V
[...][c][a][b]
*/
public class SWAP extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        Slot_ slot1 = stack.popSlot();
        Slot_ slot2 = stack.popSlot();

        stack.pushSlot(slot1);
        stack.pushSlot(slot2);
    }
}
