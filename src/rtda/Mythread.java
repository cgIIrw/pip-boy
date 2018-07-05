package rtda;

public class Mythread {
    private int pc;
    private Mystack mystack;

    public Mythread() {
        mystack = new Mystack(1024);

    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public void pushMyframe(Myframe frame) {
        this.mystack.push(frame);
    }

    public Myframe popMyframe() {
        return this.mystack.pop();

    }

    public Myframe currentMyframe() {
        return this.mystack.top();
    }
}
