package rtda;

public class Myframe {

    // 前一个栈帧
    Myframe lower;
    LocalVars localVars;
    OperandStack operandStack;

    public Myframe(int maxStack, int maxLocals) {
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public LocalVars getLocalVars() {
        return this.localVars;
    }

    public OperandStack getOperandStack() {
        return this.operandStack;
    }
}
