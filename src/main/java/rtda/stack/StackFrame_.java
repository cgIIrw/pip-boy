package rtda.stack;

import rtda.methodarea.Method_;

// 虚拟机栈的栈帧
public class StackFrame_ {

    // 对栈而言的前一个栈帧
    private StackFrame_ nextFrame;
    private LocalVars_ localVars;
    private OperandStack_ operandStack;
    // 当前所在线程
    private Thread_ thread;
    private Method_ method;
    private int nextPC;

    public StackFrame_(Thread_ thread, Method_ method) {
        this.thread = thread;
        this.method = method;

        this.localVars = new LocalVars_(method.getMaxLocals());
        this.operandStack = new OperandStack_(method.getMaxStack());
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

    public Method_ getMethod_() {
        return method;
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
