package instructions.loads.xaload;

import instructions.base.NoOperandsInstruction;
import instructions.loads.loads_tools.Tools;
import rtda.heap.Instance_;
import rtda.stack.OperandStack_;
import rtda.stack.Slot_;
import rtda.stack.StackFrame_;

public class BALOAD extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        // 数组索引
        int index = operandStack.popInt();
        // 数组引用
        Instance_ ref = operandStack.popRef();
        Tools.checkNotNil(ref);
        Slot_[] slot_s = ref.getFields().getSlots();
        Tools.checkIndex(slot_s.length, index);
        // 将数组索引为index的值压入操作数栈
        // 引用占用一个slot
        operandStack.pushInt(slot_s[index].getNum());
    }
}
