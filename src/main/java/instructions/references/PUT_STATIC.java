package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Clinit;
import rtda.methodarea.Field_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.LocalVars_;
import rtda.stack.StackFrame_;
import rtda.stack.OperandStack_;


/**
 * 需要两个操作数，第一个一个index，在运行时常量池找到符号引用，解析就知道要给哪个静态变量赋值
 * 第二个操作数就是要赋的值
 */
public class PUT_STATIC extends Index16Instruction {

    @Override
    public void execute(StackFrame_ frame) {
        Method_ currentMethod = frame.getMethod_();
        InstanceKlass_ currentClass = currentMethod.getInstanceKlass_();
        RuntimeConstantPool_ cp = currentClass.getRuntimeConstantPool();
        FieldRef fieldRef = (FieldRef)((cp.getConstant(index)).getVal());
        Field_ field = fieldRef.resolvedField();
        InstanceKlass_ class1 = field.getInstanceKlass_();

        // 类初始化
        if (!class1.getClinitFlag()
//                && class1.getStaticSlotCount() != 0
        ) {
            Clinit.revertNextPc(frame);
            Clinit.clinitClass(frame.getThread_(), class1);
            return;
        }

        if (!field.isStatic()) {
            throw new IncompatibleClassChangeError("java.lang.IncompatibleClassChangeError");
        }

        if (field.isFinal()) {
            if (currentClass != class1 || !currentMethod.getName().equals("<clinit>")) {
                throw new IllegalAccessError("java.lang.IllegalAccessError");
            }
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
