package instructions.control.switch_;

import instructions.base.Branch;
import instructions.base.BranchInstruction;
import instructions.base.Instruction;
import instructions.utils.BytecodeReader;
import rtda.stack.StackFrame_;

// switch根据实际情况可以解析为两种不同指令来解释执行，
// 一种是tableswitch
public class TABLE_SWITCH implements Instruction {
    private int defaultOffset;
    private int low;
    private int high;
    private int[] jumpOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        low = reader.readInt32();
        high = reader.readInt32();
        int jumpOffsetsLength = high - low + 1;
        jumpOffsets = reader.readInt32s(jumpOffsetsLength);
    }

    @Override
    public void execute(StackFrame_ frame) {
        // index是获取的被推入栈顶的参数，它和low和high都是绝对数
        int index = frame.getOperandStack().popInt();
        // BytecodeReader内置或者计算出来的计游标都是相对数、偏移
        // 量，在实际执行指令的时候会加上线程私有的pc
        int offset;
        if ((index <= high) && (index >= low)) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        Branch.branch(frame, offset);
    }
}
