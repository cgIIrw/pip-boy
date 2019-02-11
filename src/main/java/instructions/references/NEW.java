package instructions.references;

import instructions.base.Index16Instruction;
import rtda.methodarea.Clinit;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.heap.Instance_;

public class NEW extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        ClassRef cf = (ClassRef) ((cp.getConstant(index)).getVal());
        InstanceKlass_ instanceKlass_ = cf.resolvedClass();

        // 类初始化
        if (!instanceKlass_.getClinitFlag()) {
//            Clinit.revertNextPc(frame);
            Clinit.clinitClass(frame.getThread_(), instanceKlass_);
//            return;
        }

        if (instanceKlass_.isAbstract() || instanceKlass_.isInterface()) {
            throw new InstantiationError("抽象类或接口不能实例化！");
        }

        Instance_ ref = instanceKlass_.newObject();

        frame.getOperandStack().pushRef(ref);
    }
}
