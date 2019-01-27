package instructions.stores.xastore;

import instructions.base.NoOperandsInstruction;
import instructions.loads.loads_tools.Tools;
import rtda.heap.Instance_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

public class DASTORE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        // 需要存储在数组中的引用
        double val = operandStack.popDouble();
        int index = operandStack.popInt();
        Instance_ arrRef = operandStack.popRef();
        Tools.checkNotNil(arrRef);
        Tools.checkIndex(arrRef.getFields().getSlots().length, index);
        // 设置slots数组index索引的引用值为val
        long temp = Double.doubleToLongBits(val);
        (arrRef.getFields().getSlots())[index * 2].setNum((int) (temp >> 32));
        (arrRef.getFields().getSlots())[index * 2 + 1].setNum((int) temp);
    }
}
