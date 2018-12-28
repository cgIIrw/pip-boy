package rtda.heap;

import rtda.methodarea.Class_;
import rtda.stack.LocalVars_;

// 实例对象的表示方法
public class Instance_ {
    private Class_ class_;
    private LocalVars_ fields;

    public Instance_(Class_ class_) {
        this.class_ = class_;
        fields = new LocalVars_(class_.getInstanceSlotCount());
    }

    public LocalVars_ getFields() {
        return fields;
    }

    public boolean isInstanceOf(Class_ s) {
        return s.isAssignableFrom(this.class_);
    }

    public Class_ getClass_() {
        return class_;
    }
}
