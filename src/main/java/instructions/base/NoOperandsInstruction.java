package instructions.base;

import instructions.utils.BytecodeReader;

public abstract class NoOperandsInstruction implements Instruction {

    @Override
    public void fetchOperands(BytecodeReader reader) {
    }
}
