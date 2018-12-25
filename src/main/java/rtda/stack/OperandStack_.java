package rtda.stack;

import rtda.heap.Instance_;

// 实现操作数栈
public class OperandStack_ {
    private int size;
    // 操作数栈内部和局部变量表一样，也维护一个Slot数组
    private Slot_[] slots;

    public OperandStack_(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot_[maxStack];
        }
        assert slots != null;
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot_();
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
        this.slots[this.size].setNum((int) val);
        this.slots[this.size + 1].setNum((int) (val >> 32));
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        int low = (int) (this.slots[this.size].getNum());
        int high = (int) (this.slots[this.size + 1].getNum());
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

    public void pushRef(Instance_ ref) {
        this.slots[this.size].setRef(ref);
        this.size++;
    }

    public Instance_ popRef() {
        this.size--;
        Instance_ ref = this.slots[this.size].getRef();
        this.slots[this.size].setRef(null);
        return ref;
    }

    public void pushSlot(Slot_ slot) {
        this.slots[this.size].setRef(slot.getRef());
        this.slots[this.size].setNum(slot.getNum());
        this.size++;
    }

    public Slot_ popSlot() {
        this.size--;
        return this.slots[this.size];
    }

    public Instance_ getRefFromTop(int index) {
        return this.slots[this.size - 1 - index].getRef();
    }
}
