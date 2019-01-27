package instructions.loads.loads_tools;

import rtda.heap.Instance_;

public interface Tools {
    static void checkNotNil(Instance_ ref) {
        if (ref == null)
            throw new NullPointerException();
    }

    static void checkIndex(int arrLen, int index) {
        if (index < 0 || index >= arrLen)
            throw new ArrayIndexOutOfBoundsException();
    }
}
