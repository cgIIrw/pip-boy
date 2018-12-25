package rtda;

// Java程序的线程抽象
public class Thread_ {
    private int pc;
    private VirtualMachineStack_ virtualMachineStack_;

    public Thread_() {
        virtualMachineStack_ = new VirtualMachineStack_(1024);
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushStackFrame_(StackFrame_ frame) {
        this.virtualMachineStack_.push(frame);
    }

    public StackFrame_ popStackFrame_() {
        return this.virtualMachineStack_.pop();
    }

    public StackFrame_ currentStackFrame_() {
        return this.virtualMachineStack_.getTop();
    }

    public StackFrame_ getTopFrame() {
        return this.virtualMachineStack_.getTop();
    }
}
