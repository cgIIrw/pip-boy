package rtda.heap;

import rtda.methodarea.Class_;
import rtda.methodarea.coutfields_utils.InstanceFieldsCounter;
import rtda.stack.LocalVars_;

// 用此类开辟实例对象的内存空间
public class Instance_ {
    private Class_ class_;
    private LocalVars_ fields;

    public Instance_(Class_ class_) {
        this.class_ = class_;
        class_.setInstanceSlotCount(InstanceFieldsCounter.countsInstanceField(class_));
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
