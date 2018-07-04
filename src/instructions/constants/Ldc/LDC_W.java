package instructions.constants.Ldc;

/**
 * 和LDC不同之处在于操作数它取16位
 */
import classfile.CreateConstantInfo;
import instructions.base.Index16Instruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.Constant;
import rtda.heap.RuntimeConstantPool;

public class LDC_W extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().runtimeConstantPool;
        Constant c = cp.getConstants(index);

        switch (c.getType()) {
            case CreateConstantInfo.CONSTANT_Integer:
                stack.pushInt((int)(c.getVal()));
                break;
            case CreateConstantInfo.CONSTANT_Float:
                stack.pushFloat((float)(c.getVal()));
                break;
//            case CreateConstantInfo.CONSTANT_String:
//                break;
//            case CreateConstantInfo.CONSTANT_Class:
//                break;
//            case CreateConstantInfo.CONSTANT_Fieldref:
//                break;
//            case CreateConstantInfo.CONSTANT_Methodref:
//                break;
//            case CreateConstantInfo.CONSTANT_InterfaceMethodref:
//                break;
            default:
                break;
            // todo
        }
    }
}
