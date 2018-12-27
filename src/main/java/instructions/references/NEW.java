package instructions.references;

import instructions.base.Index16Instruction;
import rtda.stack.StackFrame_;
import rtda.heap.ClassRef;
import rtda.heap.Class_;
import rtda.heap.Instance_;
import rtda.heap.RuntimeConstantPool;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool cp = frame.getMyMethod().getClass_().getRuntimeConstantPool();
        ClassRef cf = (ClassRef)((cp.getConstant(index)).getVal());
        Class_ class_ = cf.resolvedClass();

        // todo 初始化的一个判断

        if (class_.isAbstract() || class_.isInterface()) {
            throw new InstantiationError("java.lang.InstantiationError");
        }

        Instance_ ref = class_.newObject();
        frame.getOperandStack().pushRef(ref);
    }
}
