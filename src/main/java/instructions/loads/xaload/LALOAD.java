package instructions.loads.xaload;

import instructions.base.NoOperandsInstruction;
import instructions.loads.loads_tools.Tools;
import rtda.heap.Instance_;
import rtda.stack.OperandStack_;
import rtda.stack.Slot_;
import rtda.stack.StackFrame_;

// long
public class LALOAD extends NoOperandsInstruction {
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
        // long引用占用两个slot
        long long01 = slot_s[index * 2].getNum();
        int long02 = slot_s[index * 2 + 1].getNum();
        long num = long01 << 32 | long02;
        operandStack.pushLong(num);
    }
}
