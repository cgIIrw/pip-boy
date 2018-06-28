package instructions.stack.pop;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;
import rtda.OperandStack;

public class POP2 extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack oper = frame.getOperandStack();
        oper.popSlot();
        oper.popSlot();
    }
}
