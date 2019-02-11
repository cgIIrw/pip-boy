package rtda.methodarea.countfields_utils;

import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Field_;

// // 实例字段计数器，计数的同时给字段附上id号(从0开始)
public class InstanceFieldsCounter {

    public static int countsInstanceField(InstanceKlass_ instanceKlass_) {
        int slotCounts = 0;
        if (instanceKlass_.getSuperClass() != null)
            slotCounts = countsInstanceField(instanceKlass_.getSuperClass());

        for (Field_ field : instanceKlass_.getFields()) {
            if (!field.isStatic()) {
                field.setSlotId(slotCounts);
                slotCounts++;
                if (field.isLongOrDouble())
                    slotCounts++;
            }
        }
        return slotCounts;
    }
}
