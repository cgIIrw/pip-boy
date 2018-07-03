package instructions.references;

/**
 * 三个操作数，前两个是常量池索引和变量值，第三个是对象引用
 */

import instructions.base.Index16Instruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.*;

public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        MyMethod currentMethod = frame.getMyMethod();
        Myclass currentClass = currentMethod.getMyclass();
        RuntimeConstantPool cp = currentClass.runtimeConstantPool;
        FieldRef fieldRef = (FieldRef) (cp.getConstants(index));
        MyField field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        if (field.isFinal()) {
            if ((currentClass != field.getMyclass()) || (currentMethod.getName() != "<init>")) {
                throw new IllegalAccessError("java.lang.IllegalAccessError");
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                int val = stack.popInt();
                Myobject ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setInt(slotId, val);
                break;
            }
            case 'F': {
                float val = stack.popFloat();
                Myobject ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setFloat(slotId, val);
                break;
            }
            case 'J': {
                long val = stack.popLong();
                Myobject ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setLong(slotId, val);
                break;
            }
            case 'D': {
                double val = stack.popDouble();
                Myobject ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setDouble(slotId, val);
                break;
            }
            case 'L':
            case '[': {
                Myobject val = stack.popRef();
                Myobject ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setRef(slotId, val);
                break;
            }
            default:
                break;
        }
    }
}
