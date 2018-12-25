package instructions.references;

import instructions.base.Index16Instruction;
import rtda.LocalVars;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.*;

public class GET_FIELD extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)((cp.getConstant(index)).getVal());
        MyField field = fieldRef.resolvedField();

        if (field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        OperandStack stack = frame.getOperandStack();
        Myobject ref = stack.popRef();
        if (ref == null) {
            throw new NullPointerException("java.lang.NullPointerException");
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars slots = ref.getFields();
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
