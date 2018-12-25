package rtda.stack;

import rtda.heap.MyMethod;

// 虚拟机栈的栈帧
public class StackFrame_ {

    // 对栈而言的前一个栈帧
    private StackFrame_ nextFrame;
    private LocalVars_ localVars;
    private OperandStack_ operandStack;
    // 当前所在线程
    private Thread_ thread;
    private MyMethod myMethod;
    private int nextPC;

    public StackFrame_(Thread_ thread, MyMethod myMethod) {
        this.thread = thread;
        this.myMethod = myMethod;

        this.localVars = new LocalVars_(myMethod.getMaxLocals());
        this.operandStack = new OperandStack_(myMethod.getMaxStack());
    }

    public StackFrame_(Thread_ thread, int maxLocals, int maxStack) {
        this.thread = thread;
        localVars = new LocalVars_(maxLocals);
        operandStack = new OperandStack_(maxStack);
    }

    public LocalVars_ getLocalVars() {
        return this.localVars;
    }

    public OperandStack_ getOperandStack() {
        return this.operandStack;
    }

    public Thread_ getThread_() {
        return thread;
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

    public StackFrame_ getNextFrame() {
        return nextFrame;
    }

    public void setNextFrame(StackFrame_ nextFrame) {
        this.nextFrame = nextFrame;
    }
}
