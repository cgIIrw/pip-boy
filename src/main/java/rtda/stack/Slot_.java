package rtda.stack;

import rtda.heap.Instance_;

// 代表局部变量表和操作数栈分配内存使用的最小单位的类
public class Slot_ {

    // 这里把所有基本变量类型都转化为num来处理
    private int num;
    private Instance_ ref;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Instance_ getRef() {
        return ref;
    }

    public void setRef(Instance_ ref) {
        this.ref = ref;
    }
}
