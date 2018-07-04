package instructions.base;

/**
 * 表示跳转指令，Offset字段存放跳转偏移量
 */
public abstract class BranchInstruction implements Instruction{
    int offset;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        offset = reader.readInt16();
    }
}
