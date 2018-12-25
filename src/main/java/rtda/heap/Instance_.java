package rtda.heap;

import rtda.LocalVars;

// 实例对象的表示方法
public class Instance_ {
    private Class_ class_;
    private LocalVars fields;

    public Instance_(Class_ class_) {
        this.class_ = class_;
        fields = new LocalVars(class_.getInstanceSlotCount());
    }

    public LocalVars getFields() {
        return fields;
    }

    public boolean isInstanceOf(Class_ s) {
        return s.isAssignableFrom(this.class_);
    }

    public Class_ getMyclass() {
        return class_;
    }
}
