package rtda;

import rtda.heap.MyMethod;

public class Myframe {

    // 前一个栈帧
    private Myframe lower;
    private LocalVars localVars;
    private OperandStack operandStack;
    private Mythread mythread; // 当前所在线程
    private MyMethod myMethod;
    private int nextPC; //


    public Myframe(Mythread mythread, MyMethod myMethod) {
        this.mythread = mythread;
        this.myMethod = myMethod;

        this.localVars = new LocalVars(myMethod.getMaxLocals());
        this.operandStack = new OperandStack(myMethod.getMaxStack());
    }

    public Myframe(Mythread mythread, int maxLocals, int maxStack) {
        this.mythread = mythread;
        localVars = new LocalVars(maxLocals);
        operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return this.localVars;
    }

    public OperandStack getOperandStack() {
        return this.operandStack;
    }

    public Mythread getMythread() {
        return mythread;
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

    public Myframe getLower() {
        return lower;
    }

    public void setLower(Myframe lower) {
        this.lower = lower;
    }
}
