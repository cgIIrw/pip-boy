package instructions.control.return_ins;

import instructions.base.NoOperandsInstruction;
import rtda.Myframe;

public class RETURN extends NoOperandsInstruction {
    @Override
    public void execute(Myframe frame) {
        frame.getMythread().popMyframe();
    }
}
