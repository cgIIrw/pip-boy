package rtda.methodarea.countfields_utils;

import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Field_;

// 静态字段计数器，计数的同时给字段附上id号(从0开始)
public class StaticFieldsCounter {
    public static void countsStaticFields(InstanceKlass_ instanceKlass_) {
        int slotCount = 0;
        for (Field_ field : instanceKlass_.getFields()) {
            if (field.isStatic()) {
                field.setSlotId(slotCount);
                slotCount++;
                if (field.isLongOrDouble())
                    slotCount++;
            }
        }
        instanceKlass_.setStaticSlotCount(slotCount);
    }
}
