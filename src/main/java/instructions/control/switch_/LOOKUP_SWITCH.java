package instructions.control.switch_;

import instructions.base.Branch;
import instructions.base.Instruction;
import instructions.utils.BytecodeReader;
import rtda.stack.StackFrame_;

public class LOOKUP_SWITCH implements Instruction {
    private int defaultOffset;
    private int npairs;
    private int[] matchOffsets;

    @Override
    public void fetchOperands(BytecodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt32();
        npairs = reader.readInt32();
        // 由于case的值不在是连续的，所以使用两两相结对的方法，
        // 数组中两个连续的元素，一个用来存储case的值，一个用
        // 来存储跳转的相对位置(再次强调，这里的位置和bpc一样，
        // 是相对位置)
        matchOffsets = reader.readInt32s(npairs * 2);
    }

    @Override
    public void execute(StackFrame_ frame) {
        int key = frame.getOperandStack().popInt();
        int offset = defaultOffset;
        for (int i = 0; i < npairs * 2; i += 2) {
            if (key == matchOffsets[i]) {
                // 一旦传入的参数匹配case中的某个值，
                // offset从默认更新为需要跳转的新值
                offset = matchOffsets[i + 1];
                break;
            }
        }
        Branch.branch(frame, offset);
    }
}
