package rtda;

import rtda.heap.Myobject;

public class LocalVars {
    private Slot[] localVars;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            localVars = new Slot[maxLocals];
            for (int i = 0; i < localVars.length; i++) {
                localVars[i] = new Slot();
            }
        }
    }

    public void setInt(int index, int val) {
        localVars[index].setNum(val);
    }

    public int getInt(int index) {
        return localVars[index].getNum();
    }

    public void setFloat(int index, float val) {
         int temp = Float.floatToIntBits(val);
        localVars[index].setNum(temp);

    }

    public float getFloat(int index) {
        float temp = Float.intBitsToFloat(localVars[index].getNum());
        return temp;
    }

    public void setLong(int index, long val) {
        localVars[index].setNum((int) (val));
        localVars[index + 1].setNum((int) (val >> 32));
    }

    public long getLong(int index) {
        int low = localVars[index].getNum();
        int high = localVars[index + 1].getNum();
        return ((high & 0xffffffffL)<< 32) | (low & 0xffffffffL);
//        return ((high << 32) | low) & 0xffffffffffffffffL;
    }

    // 现转化为long，然后再进行处理
    public void setDouble(int index, double val) {
        long temp = Double.doubleToLongBits(val);
        setLong(index, temp);
    }

    public double getDouble(int index) {
        long temp = getLong(index);
        return Double.longBitsToDouble(temp);
    }

    public void setRef(int index, Myobject ref) {
        localVars[index].setRef(ref);
    }

    public Myobject getRef(int index) {
        return localVars[index].getRef();
    }
}
