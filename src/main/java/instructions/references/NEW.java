package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.methodarea.Class_;
import rtda.heap.Instance_;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
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
