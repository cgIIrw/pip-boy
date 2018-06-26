package rtda;

public class LocalVars {
    Slot[] localVars;

    public LocalVars(int maxLocals) {
        if (maxLocals > 0) {
            localVars = new Slot[maxLocals];
        }
    }

    public void setInt(int index, int val) {
        localVars[index].num = val;
    }

    public int getInt(int index) {
        return localVars[index].num;
    }

    public void setFloat(int index, int val) {
         int temp = Float.floatToIntBits(val);
        localVars[index].num = temp;

    }

    public float getFloat(int index) {
        float temp = Float.intBitsToFloat(localVars[index].num);
        return temp;
    }

    public void setLong(int index, long val) {
        localVars[index].num = (int) (val);
        localVars[index + 1].num = (int) (val >> 32);
    }

    public long getLong(int index) {
        int low = localVars[index].num;
        int high = localVars[index + 1].num;
        return (high << 32) | low;
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
        localVars[index].ref = ref;
    }

    public Myobject getRef(int index) {
        return localVars[index].ref;
    }
}
