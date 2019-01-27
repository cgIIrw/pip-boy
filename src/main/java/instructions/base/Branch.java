package instructions.base;

import rtda.stack.StackFrame_;

public class Branch {

    public static void branch(StackFrame_ stackFrame, int offset) {
        int pc = stackFrame.getThread_().getPc();
        int nextPc = pc + offset;
        stackFrame.setNextPC(nextPc);
    }
}
