package instructions.constants;

import instructions.utils.BytecodeReader;
import instructions.base.Instruction;
import rtda.stack.StackFrame_;

public class BIPUSH implements Instruction {
    private int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.val = reader.readUint8();
    }

    @Override
    public void execute(StackFrame_ frame) {
        // 将有符号转换为无符号
        frame.getOperandStack().pushInt(val);
    }
}
