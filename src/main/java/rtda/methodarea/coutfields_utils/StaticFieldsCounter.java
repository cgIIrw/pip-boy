package rtda.methodarea.coutfields_utils;

import rtda.methodarea.Class_;
import rtda.methodarea.Field_;

// 静态字段计数器，计数的同时给字段附上id号(从0开始)
public class StaticFieldsCounter {
    public static void countsStaticFields(Class_ class_) {
        int slotCount = 0;
        for (Field_ field : class_.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotCount);
                slotCount++;
                if (field.isLongOrDouble())
                    slotCount++;
            }
        }
        class_.setStaticSlotCount(slotCount);
    }
}
