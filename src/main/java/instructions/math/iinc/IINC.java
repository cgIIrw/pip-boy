package instructions.math.iinc;

import instructions.utils.BytecodeReader;
import instructions.base.Instruction;
import rtda.stack.LocalVars_;
import rtda.stack.StackFrame_;

// iinc给局部变量表中的int变量增加常量值
public class IINC implements Instruction {
    private int index;
    private int constant;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        this.index = reader.readUint8();
        this.constant = reader.readUint8();
    }

    @Override
    public void execute(StackFrame_ frame) {
        LocalVars_ localVars = frame.getLocalVars();
        int val = localVars.getInt(this.index);
        val += this.constant;
        localVars.setInt(this.index, val);
    }
}
