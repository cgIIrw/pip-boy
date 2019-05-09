package rtda.stack;

import rtda.heap.Instance_;

// 局部变量表，通过该类来访问Slot数组
public class LocalVars_ {
    private Slot_[] slots;

    public LocalVars_(int maxLocals) {
        if (maxLocals > 0) {
            slots = new Slot_[maxLocals];
            for (int i = 0; i < slots.length; i++) {
                slots[i] = new Slot_();
            }
        }
    }

    public void setInt(int index, int val) {
        slots[index].setNum(val);
    }

    public int getInt(int index) {
        return slots[index].getNum();
    }

    public void setFloat(int index, float val) {
        int temp = Float.floatToIntBits(val);
        slots[index].setNum(temp);
    }

    public float getFloat(int index) {
        return Float.intBitsToFloat(slots[index].getNum());
    }

    public void setLong(int index, long val) {
        slots[index].setNum((int) (val));
        slots[index + 1].setNum((int) (val >> 32));
    }

    public long getLong(int index) {
        int low = slots[index].getNum();
        int high = slots[index + 1].getNum();
        return ((high & 0xffffffffL) << 32) | (low & 0xffffffffL);
//        return ((high << 32) | low) & 0xffffffffffffffffL;
    }

    // 先转化为long，然后再进行处理
    public void setDouble(int index, double val) {
        long temp = Double.doubleToLongBits(val);
        setLong(index, temp);
    }

    public double getDouble(int index) {
        long temp = getLong(index);
        return Double.longBitsToDouble(temp);
    }

    public void setRef(int index, Instance_ ref) {
        if (ref != null)
            slots[index].setRef(ref);
    }

    public Instance_ getRef(int index) {
        return slots[index].getRef();
    }

    public void setSlot(int index, Slot_ slot) {
        this.slots[index] = slot;
    }

    public Slot_[] getSlots() {
        return slots;
    }
}
