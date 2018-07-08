package instructions.constants.Ldc;

import classfile.CreateConstantInfo;
import instructions.base.Index8Instruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.Constant;
import rtda.heap.Myclass;
import rtda.heap.RuntimeConstantPool;

public class LDC extends Index8Instruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().getRuntimeConstantPool();
        Constant c = cp.getConstant(index);

        switch (c.getType()) {
            case CreateConstantInfo.CONSTANT_Integer:
                stack.pushInt((int)((long)(c.getVal()) & 0xffffffff));
                break;
            case CreateConstantInfo.CONSTANT_Float:
                stack.pushFloat((float)(c.getVal()));
                break;
//            case CreateConstantInfo.CONSTANT_Long:
//                break;
//            case CreateConstantInfo.CONSTANT_Double:
//                break;
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
