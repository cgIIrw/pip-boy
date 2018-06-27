package instructions.base;

import rtda.Myframe;

public interface Instruction {
    // 从字节码中提取操作数
    void fetchOperands(BytecodeReader reader);

    // 执行指令
    void execute(Myframe frame);
}
