package instructions.math.iinc;

import instructions.base.BytecodeReader;
import instructions.base.Instruction;
import rtda.LocalVars;
import rtda.Myframe;

public class IINC implements Instruction {
    int index;
    int cons;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
        // 这里readInt8()是有符号的
        this.cons = reader.readInt8();

    }

    @Override
    public void execute(Myframe frame) {
        LocalVars localVars = frame.getLocalVars();
        int val = localVars.getInt(this.index);
        val += this.cons;
        localVars.setInt(this.index, val);
    }
}
