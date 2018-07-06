package rtda;

import rtda.heap.Myobject;

// 实现操作数栈
public class OperandStack {
    private int size;
    private Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
        }
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public void pushInt(int val) {
        this.slots[size].setNum(val);
        this.size++;
    }

    public int popInt() {
        size--;
        return this.slots[this.size].getNum();
    }

    // 处理float
    public void pushFloat(float val) {
        int temp = Float.floatToIntBits(val);
        this.slots[size].setNum(temp);
        this.size++;
    }

    public float popFloat() {
        this.size--;
        int temp = this.slots[this.size].getNum();
        return Float.intBitsToFloat(temp);
    }

    //
    public void pushLong(long val) {
        this.slots[this.size].setNum((int)val);
        this.slots[this.size + 1].setNum((int)(val >> 32));
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        int low = (int)(this.slots[this.size].getNum());
        int high = (int)(this.slots[this.size + 1].getNum());
        return ((high & 0xffffffffL) << 32) | (low & 0xffffffffL);
    }

    public void pushDouble(double val) {
        long temp = Double.doubleToLongBits(val);
        pushLong(temp);
    }

    public double popDouble() {
        long temp = popLong();
        return Double.longBitsToDouble(temp);
    }

    public void pushRef(Myobject ref) {
        this.slots[this.size].setRef(ref);
        this.size++;
    }

    public Myobject popRef() {
        this.size--;
        Myobject ref = this.slots[this.size].getRef();
        this.slots[this.size].setRef(null);
        return ref;
    }

    public void pushSlot(Slot slot) {
        this.slots[this.size] = slot;
        this.size++;
    }

    public Slot popSlot() {
        this.size--;
        return this.slots[this.size];
    }

    public Myobject getRefFromTop(int index) {
        return this.slots[this.size - 1 - index].getRef();
    }
}
