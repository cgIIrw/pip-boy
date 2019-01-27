package instructions.base;

import instructions.utils.BytecodeReader;

// 通常为局部变量的访问
public abstract class Index8Instruction implements Instruction {

    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        // 该指令最常见的场景就是存储以及加载局部变量表，
        // index表示局部变量表的索引位置
        this.index = reader.readUint8();
    }
}
