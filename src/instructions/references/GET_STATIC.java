package instructions.references;

import instructions.base.Index16Instruction;
import rtda.LocalVars;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.FieldRef;
import rtda.heap.MyField;
import rtda.heap.Myclass;
import rtda.heap.RuntimeConstantPool;

public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().runtimeConstantPool;
        FieldRef fieldRef = (FieldRef)(cp.getConstants(index));
        MyField field = fieldRef.resolvedField();
        Myclass class1 = field.getMyclass();
        // todo

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
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
