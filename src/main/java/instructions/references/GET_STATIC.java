package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.Clinit;
import rtda.stack.LocalVars_;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.Field_;
import rtda.methodarea.Class_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

public class GET_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)(((cp.getConstant(index))).getVal());
        Field_ field = fieldRef.resolvedField();
        Class_ class1 = field.getClass_();

        // 类初始化
        if (!class1.getClinitFlag()) {
//            Clinit.revertNextPc(frame);
            Clinit.clinitClass(frame.getThread_(), class1);
//            return;
        }

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
