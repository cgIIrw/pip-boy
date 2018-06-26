package rtda;

public class Mystack {

    int maxSize;
    int size;
    Myframe top;

    public Mystack(int maxSize) {
        this.maxSize = maxSize;
    }

    public void push(Myframe myframe) {
        if (size >= maxSize) {
            throw new RuntimeException();
        }

        if (this.top != null) {
            myframe.lower = this.top;
        }

        this.top = myframe;
        size++;
    }

    public Myframe pop() {
        if (this.top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }

        Myframe temptop = this.top;
        this.top = temptop.lower;
        temptop.lower = null;
        size--;

        return temptop;
    }

    public Myframe top() {
        if (this.top == null) {
            throw new RuntimeException("jvm stack is empty!");
        }
        return this.top;
    }
}
