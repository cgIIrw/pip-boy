package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Myframe;
import rtda.heap.ClassRef;
import rtda.heap.Myclass;
import rtda.heap.Myobject;
import rtda.heap.RuntimeConstantPool;

public class NEW extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().getRuntimeConstantPool();
        ClassRef cf = (ClassRef)((cp.getConstant(index)).getVal());
        Myclass myclass = cf.resolvedClass();

        // todo 初始化的一个判断

        if (myclass.isAbstract() || myclass.isInterface()) {
            throw new InstantiationError("java.lang.InstantiationError");
        }

        Myobject ref = myclass.newObject();
        frame.getOperandStack().pushRef(ref);
    }
}
