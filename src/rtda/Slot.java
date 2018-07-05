package rtda;

import rtda.heap.Myobject;

public class Slot {

    // 这里把所有基本变量类型都转化为num来处理
    private int num;
    private Myobject ref;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Myobject getRef() {
        return ref;
    }

    public void setRef(Myobject ref) {
        this.ref = ref;
    }
}
