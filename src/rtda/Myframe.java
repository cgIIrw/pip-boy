package rtda;

import rtda.heap.MyMethod;

public class Myframe {

    // 前一个栈帧
    Myframe lower;
    LocalVars localVars;
    OperandStack operandStack;
    Mythread mythread; // 当前所在线程
    MyMethod myMethod;
    int nextPC; //


    public Myframe(Mythread mythread, MyMethod myMethod) {
        this.mythread = mythread;
        this.myMethod = myMethod;

        this.localVars = new LocalVars(myMethod.getMaxLocals());
        this.operandStack = new OperandStack(myMethod.getMaxStack());
    }

    public Myframe(Mythread mythread, int maxLocals, int maxStack) {
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
}
