package instructions.base;

import instructions.utils.BytecodeReader;

// 表示跳转指令，Offset字段存放跳转偏移量，抽象类
public abstract class BranchInstruction implements Instruction {
    private int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // 获取跳转偏移量
        this.offset = reader.readUint16();
    }

    public int getOffset() {
        return offset;
    }
}
