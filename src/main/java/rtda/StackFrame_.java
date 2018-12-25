package rtda;

import rtda.heap.MyMethod;

public class StackFrame_ {

    // 前一个栈帧
    private StackFrame_ lower;
    private LocalVars localVars;
    private OperandStack operandStack;
    private Thread_ thread_; // 当前所在线程
    private MyMethod myMethod;
    private int nextPC; //


    public StackFrame_(Thread_ thread_, MyMethod myMethod) {
        this.thread_ = thread_;
        this.myMethod = myMethod;

        this.localVars = new LocalVars(myMethod.getMaxLocals());
        this.operandStack = new OperandStack(myMethod.getMaxStack());
    }

    public StackFrame_(Thread_ thread_, int maxLocals, int maxStack) {
        this.thread_ = thread_;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return this.localVars;
    }

    public OperandStack getOperandStack() {
        return this.operandStack;
    }

    public Thread_ getThread_() {
        return thread_;
    }

    public MyMethod getMyMethod() {
        return myMethod;
    }

    public int getNextPC() {
        return nextPC;
    }

    public void setNextPC(int nextPC) {
        this.nextPC = nextPC;
    }

    public StackFrame_ getLower() {
        return lower;
    }

    public void setLower(StackFrame_ lower) {
        this.lower = lower;
    }
}
