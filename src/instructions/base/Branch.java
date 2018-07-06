package instructions.base;

import rtda.Myframe;

public class Branch {

    public static void branch(Myframe myframe, int offset) {
        int pc = myframe.getMythread().getPc();
        int nextPc = pc + offset;
        myframe.setNextPC(nextPc);
    }
}
