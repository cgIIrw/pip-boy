package instructions.constants.Ldc;

import classfile.constantpool.ConstantInfoFactory;
import instructions.base.Index16Instruction;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.methodarea.rtcp.Constant;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

import java.math.BigInteger;


// 从运行时常量池加载long和double
public class LDC2_W extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        Constant c = cp.getConstant(index);

        switch (c.getType()) {
            case ConstantInfoFactory.CONSTANT_Long:
                stack.pushLong(((BigInteger) (c.getVal())).longValue());
                break;
            case ConstantInfoFactory.CONSTANT_Double:
                stack.pushDouble(((double) (c.getVal())));
                break;
            default:
                throw new ClassFormatError();
        }
    }
}
