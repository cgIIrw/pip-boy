package rtda.heap;

import rtda.LocalVars;

public class Myobject {
    private Myclass myclass;
    private LocalVars fields;

    public Myobject(Myclass myclass) {
        this.myclass = myclass;
        fields = new LocalVars(myclass.getInstanceSlotCount());
    }

    public LocalVars getFields() {
        return fields;
    }

    public boolean isInstanceOf(Myclass s) {
        return s.isAssignableFrom(this.myclass);
    }

    public Myclass getMyclass() {
        return myclass;
    }
}
