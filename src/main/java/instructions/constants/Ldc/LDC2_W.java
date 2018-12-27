package instructions.constants.Ldc;

import classfile.constantpool.ConstantInfoFactory;
import instructions.base.Index16Instruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.heap.Constant;
import rtda.heap.RuntimeConstantPool;

public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        RuntimeConstantPool cp = frame.getMyMethod().getClass_().getRuntimeConstantPool();
        Constant c = cp.getConstant(index);

        switch (c.getType()) {
            case ConstantInfoFactory.CONSTANT_Long:
                stack.pushLong(((long)(c.getVal())));
                break;
            case ConstantInfoFactory.CONSTANT_Double:
                stack.pushDouble(((double)(c.getVal())));
                break;
            default:
                throw new ClassFormatError();
        }
    }
}
