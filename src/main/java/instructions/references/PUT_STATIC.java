package instructions.references;

import instructions.base.Index16Instruction;
import rtda.LocalVars;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.*;


/**
 * 需要两个操作数，第一个一个index，在运行时常量池找到符号引用，解析就知道要给哪个静态变量赋值
 * 第二个操作数就是要赋的值
 */
public class PUT_STATIC extends Index16Instruction {

    @Override
    public void execute(Myframe frame) {
        MyMethod currentMethod = frame.getMyMethod();
        Myclass currentClass = currentMethod.getMyclass();
        RuntimeConstantPool cp = currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)((cp.getConstant(index)).getVal());
        MyField field = fieldRef.resolvedField();
        Myclass class1 = field.getMyclass();
        // todo

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        if (field.isFinal()) {
            if (currentClass != class1 || currentMethod.getName() != "<clinit?") {
                throw new IllegalAccessError("java.lang.IllegalAccessError");
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars slots = class1.getStaticVars();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                slots.setInt(slotId, stack.popInt());
                break;
            case 'F':
                slots.setFloat(slotId, stack.popFloat());
                break;
            case 'J':
                slots.setLong(slotId, stack.popLong());
                break;
            case 'D':
                slots.setDouble(slotId, stack.popDouble());
            case 'L':
            case '[':
                slots.setRef(slotId, stack.popRef());
                break;
            default:
                break;
        }
    }
}
