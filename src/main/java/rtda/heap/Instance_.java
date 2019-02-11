package rtda.heap;

import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.countfields_utils.InstanceFieldsCounter;
import rtda.stack.LocalVars_;

// 用此类开辟实例对象的内存空间
public class Instance_ {
    private InstanceKlass_ instanceKlass_;
    private LocalVars_ fields;

    // 为数组的创建添加一个构造器
    public Instance_(InstanceKlass_ instanceKlass_, int slotCount) {
        this.instanceKlass_ = instanceKlass_;
        fields = new LocalVars_(slotCount);
    }

    public Instance_(InstanceKlass_ instanceKlass_) {
        this.instanceKlass_ = instanceKlass_;
        // 在给对象开辟内存空间的时候计算实例字段的个数
        instanceKlass_.setInstanceSlotCount(InstanceFieldsCounter.countsInstanceField(instanceKlass_));
        fields = new LocalVars_(instanceKlass_.getInstanceSlotCount());
    }

    public LocalVars_ getFields() {
        return fields;
    }

    public boolean isInstanceOf(InstanceKlass_ s) {
        return s.isAssignableFrom(this.instanceKlass_);
    }

    public InstanceKlass_ getInstanceKlass_() {
        return instanceKlass_;
    }
}
