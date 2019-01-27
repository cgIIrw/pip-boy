package instructions.constants;

import instructions.utils.BytecodeReader;
import instructions.base.Instruction;
import rtda.stack.StackFrame_;

public class SIPUSH implements Instruction {
    private int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readUint16();
    }

    @Override
    public void execute(StackFrame_ frame) {
        frame.getOperandStack().pushInt(this.val);
    }
}
