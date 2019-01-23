package instructions.constants.Ldc;

import classfile.constantpool.ConstantInfoFactory;
import instructions.base.Index8Instruction;
import rtda.heap.Instance_;
import rtda.heap.StringPool;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.Constant;

// 从运行时常量池加载int和float
public class LDC extends Index8Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ stack = frame.getOperandStack();
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        Constant c = cp.getConstant(index);

        switch (c.getType()) {
            case ConstantInfoFactory.CONSTANT_Integer:
                stack.pushInt((int) (c.getVal()));
                break;
            case ConstantInfoFactory.CONSTANT_Float:
                stack.pushFloat((float) (c.getVal()));
                break;
//            case ConstantInfoFactory.CONSTANT_Long:
//                break;
//            case ConstantInfoFactory.CONSTANT_Double:
//                break;
            case ConstantInfoFactory.CONSTANT_String:
                Instance_ internedRef = StringPool.jString(frame.getMethod_().getClass_()
                        .getLoader(), (String) (c.getVal()));
                stack.pushRef(internedRef);
                break;
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
