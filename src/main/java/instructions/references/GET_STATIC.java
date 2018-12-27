package instructions.references;

import instructions.base.Index16Instruction;
import rtda.stack.LocalVars_;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.heap.FieldRef;
import rtda.heap.MyField;
import rtda.heap.Class_;
import rtda.heap.RuntimeConstantPool;

public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getClass_().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)(((cp.getConstant(index))).getVal());
        MyField field = fieldRef.resolvedField();
        Class_ class1 = field.getClass_();
        // todo

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVars_ slots = class1.getStaticVars();
        OperandStack_ stack = frame.getOperandStack();

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
