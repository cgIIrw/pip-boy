package rtda;

public class VirtualMachineStack_ {

    private int maxSize;
    private int size;
    private StackFrame_ top;

    public VirtualMachineStack_(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(StackFrame_ stackFrame_) {
        if (size >= maxSize) {
            throw new RuntimeException("当前栈大小已不小于最大容量");
        }

        // 如果push之前top不为null，那么把当前的top计为欲传入的下一层
        if (this.top != null) {
            stackFrame_.setLower(this.top);
        }

        // 将top刷新
        this.top = stackFrame_;
        size++;
    }

    public StackFrame_ pop() {
        if (this.top == null) {
            throw new RuntimeException("Java虚拟机栈已空！");
        }

        StackFrame_ temptop = this.top;
        this.top = temptop.getLower();
        temptop.setLower(null);
        size--;

        return temptop;
    }

    // 返回当前栈顶帧
    public StackFrame_ getTop() {
        if (this.top == null) {
            throw new RuntimeException("Java虚拟机栈已空！");
        }
        return this.top;
    }
}
