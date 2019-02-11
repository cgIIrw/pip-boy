package instructions.references;

/**
 * 三个操作数，前两个是常量池索引和变量值，第三个是对象引用
 */

import instructions.base.Index16Instruction;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Field_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.heap.*;

public class PUT_FIELD extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        Method_ currentMethod = frame.getMethod_();
        InstanceKlass_ currentClass = currentMethod.getInstanceKlass_();
        RuntimeConstantPool_ cp = currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)((cp.getConstant(index)).getVal());
        Field_ field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        if (field.isFinal()) {
            if ((currentClass != field.getInstanceKlass_()) || (currentMethod.getName() != "<init>")) {
                throw new IllegalAccessError("java.lang.IllegalAccessError");
            }
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        OperandStack_ stack = frame.getOperandStack();

        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I': {
                int val = stack.popInt();
                Instance_ ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setInt(slotId, val);
                break;
            }
            case 'F': {
                float val = stack.popFloat();
                Instance_ ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setFloat(slotId, val);
                break;
            }
            case 'J': {
                long val = stack.popLong();
                Instance_ ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setLong(slotId, val);
                break;
            }
            case 'D': {
                double val = stack.popDouble();
                Instance_ ref = stack.popRef();
                if (ref == null) {
                    throw new NullPointerException("call "+field.getName()+" on a null object");
                }
                ref.getFields().setDouble(slotId, val);
                break;
            }
            case 'L':
            case '[': {
                Instance_ val = stack.popRef();
                Instance_ ref = stack.popRef();
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
