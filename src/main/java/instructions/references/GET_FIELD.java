package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.Field_;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.LocalVars_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;
import rtda.heap.*;

public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)((cp.getConstant(index)).getVal());
        Field_ field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        OperandStack_ stack = frame.getOperandStack();
        Instance_ ref = stack.popRef();
        if (ref == null) {
            throw new NullPointerException("java.lang.NullPointerException");
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars_ slots = ref.getFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                stack.pushInt(slots.getInt(slotId));
                break;
            case 'F':
                stack.pushFloat(slots.getFloat(slotId));
                break;
            case 'J':
                stack.pushLong(slots.getLong(slotId));
                break;
            case 'D':
                stack.pushDouble(slots.getDouble(slotId));
                break;
            case 'L':
            case '[':
                stack.pushRef(slots.getRef(slotId));
                break;
            default:
                break;
        }
    }
}
