package rtda.stack;

import rtda.heap.Instance_;

// 代表局部变量表和操作数栈分配内存使用的最小单位的类
public class Slot_ {

    // 这里把所有基本类型都转化为num来处理
    private int num;
    // 非基本类型指向引用的对象开辟的内存空间
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
