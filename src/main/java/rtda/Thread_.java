package rtda;

public class Thread_ {
    private int pc;
    private Mystack mystack;

    public Thread_() {
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
    public Myframe getTopFrame() {
        return this.mystack.top();
    }
}
