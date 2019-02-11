package instructions.references;

import instructions.base.NoOperandsInstruction;
import rtda.heap.Instance_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class ARRAY_LENGTH extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        Instance_ ref = operandStack.popRef();
        if (ref == null)
            throw new NullPointerException();

        int arrLen;
        // 获取slots数组长度
        int slotsLength = ref.getFields().getSlots().length;
        // 如果数组是Double或者Long类型，则数组长度为slots长度的二分之一
        if (ref.getInstanceKlass_().getThisClassName().equals("[D") ||
                ref.getInstanceKlass_().getThisClassName().equals("[J")) {
            arrLen = slotsLength / 2;
        } else {
            arrLen = slotsLength;
        }
        operandStack.pushInt(arrLen);
    }
}
