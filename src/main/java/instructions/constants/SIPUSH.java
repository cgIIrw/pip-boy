package instructions.constants;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Myframe;

public class SIPUSH implements Instruction {
    int val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // 这里是带符号数，比如最高位如果是非零，则为负数
        this.val = (int)(reader.readInt16() & 0xffff);
    }

    @Override
    public void execute(Myframe frame) {
        frame.getOperandStack().pushInt(this.val);
    }
}
