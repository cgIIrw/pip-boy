package rtda;

// 实现操作数栈
public class OperandStack {
    int size;
    Slot[] slots;

    public OperandStack(int maxStack) {
        if (maxStack > 0) {
            slots = new Slot[maxStack];
        }
        for (int i = 0; i < slots.length; i++) {
            slots[i] = new Slot();
        }
    }

    public void pushInt(int val) {
        this.slots[size].num = val;
        this.size++;
    }

    public int popInt() {
        size--;
        return this.slots[this.size].num;
    }

    // 处理float
    public void pushFloat(float val) {
        int temp = Float.floatToIntBits(val);
        this.slots[size].num = temp;
        this.size++;
    }

    public float popFloat() {
        this.size--;
        int temp = this.slots[this.size].num;
        return Float.intBitsToFloat(temp);
    }

    //
    public void pushLong(long val) {
        this.slots[this.size].num = (int)val;
        this.slots[this.size + 1].num = (int)(val >> 32);
        this.size += 2;
    }

    public long popLong() {
        this.size -= 2;
        int low = (int)(this.slots[this.size].num);
        int high = (int)(this.slots[this.size + 1].num);
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
        this.slots[this.size].ref = ref;
        this.size++;
    }

    public Myobject popRef() {
        this.size--;
        Myobject ref = this.slots[this.size].ref;
        this.slots[this.size].ref = null;
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
}
