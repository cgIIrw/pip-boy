package instructions.base;

public abstract class Index16Instruction implements Instruction {
    public int index;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        index = reader.readUint16();
    }
}
