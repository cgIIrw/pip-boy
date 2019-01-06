package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.Clinit;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.methodarea.Class_;
import rtda.heap.Instance_;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getClass_().getRuntimeConstantPool();
        ClassRef cf = (ClassRef) ((cp.getConstant(index)).getVal());
        Class_ class_ = cf.resolvedClass();

        // 类初始化
        if (!class_.getClinitFlag()) {
            Clinit.revertNextPc(frame);
            Clinit.clinitClass(frame.getThread_(), class_);
            return;
        }

        if (class_.isAbstract() || class_.isInterface()) {
            throw new InstantiationError("抽象类或接口不能实例化！");
        }

        Instance_ ref = class_.newObject();

        frame.getOperandStack().pushRef(ref);
    }
}
