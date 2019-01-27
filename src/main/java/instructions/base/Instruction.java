package instructions.base;

import instructions.utils.BytecodeReader;
import rtda.stack.StackFrame_;

// 指令接口
public interface Instruction {
    // 从字节码中提取操作数
    void fetchOperands(BytecodeReader reader);

    // 执行指令
    void execute(StackFrame_ frame);
}
