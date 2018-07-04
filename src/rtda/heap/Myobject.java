package rtda.heap;

import rtda.LocalVars;
import rtda.Slot;

public class Myobject {
    Myclass myclass;
    LocalVars fields;

    public Myobject(Myclass myclass) {
        this.myclass = myclass;
        fields = new LocalVars(myclass.instanceSlotCount);
    }

    public LocalVars getFields() {
        return fields;
    }

    public boolean isInstanceOf(Myclass s) {
        return s.isAssignableFrom(this.myclass);
    }
}
