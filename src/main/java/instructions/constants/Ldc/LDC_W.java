package instructions.constants.Ldc;

/**
 * 和LDC不同之处在于操作数它取16位
 */
import classfile.constantpool.ConstantInfoFactory;
import instructions.base.Index16Instruction;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.Constant;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

public class LDC_W extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        Constant c = cp.getConstant(index);

        switch (c.getType()) {
            case ConstantInfoFactory.CONSTANT_Integer:
                stack.pushInt((int)(c.getVal()));
                break;
            case ConstantInfoFactory.CONSTANT_Float:
                stack.pushFloat((float)(c.getVal()));
                break;
//            case ConstantInfoFactory.CONSTANT_String:
//                break;
//            case ConstantInfoFactory.CONSTANT_Class:
//                break;
//            case ConstantInfoFactory.CONSTANT_Fieldref:
//                break;
//            case ConstantInfoFactory.CONSTANT_Methodref:
//                break;
//            case ConstantInfoFactory.CONSTANT_InterfaceMethodref:
//                break;
            default:
                break;
            // todo
        }
    }
}
