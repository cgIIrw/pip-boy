package instructions.constants;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.Myframe;

public class BIPUSH implements Instruction {
    byte val;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // 这里是带符号数，比如最高位如果是非零，则为负数，但是可以暂且传值给val
        this.val = reader.readInt8();
    }

    @Override
    public void execute(Myframe frame) {
        // 将有符号转换为无符号
        int i = (int)(val & 0xff);
        frame.getOperandStack().pushInt(i);
    }
}
