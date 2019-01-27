package rtda.heap;

import rtda.methodarea.Class_;
import rtda.methodarea.countfields_utils.InstanceFieldsCounter;
import rtda.stack.LocalVars_;

// 用此类开辟实例对象的内存空间
public class Instance_ {
    private Class_ class_;
    private LocalVars_ fields;

    // 为数组的创建添加一个构造器
    public Instance_(Class_ class_, int slotCount) {
        this.class_ = class_;
        fields = new LocalVars_(slotCount);
    }

    public Instance_(Class_ class_) {
        this.class_ = class_;
        // 在给对象开辟内存空间的时候计算实例字段的个数
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
