package instructions.base;

import instructions.utils.BytecodeReader;

// 通常是运行时常量池的访问
public abstract class Index16Instruction implements Instruction {
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // 运行时常量池的索引通常是两个字节
        this.index = reader.readUint16();
    }
}
