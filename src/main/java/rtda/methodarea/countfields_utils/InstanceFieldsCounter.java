package rtda.methodarea.countfields_utils;

import rtda.methodarea.Class_;
import rtda.methodarea.Field_;

// // 实例字段计数器，计数的同时给字段附上id号(从0开始)
public class InstanceFieldsCounter {

    public static int countsInstanceField(Class_ class_) {
        int slotCounts = 0;
        if (class_.getSuperClass() != null)
            slotCounts = countsInstanceField(class_.getSuperClass());

        for (Field_ field : class_.getFields()) {
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
