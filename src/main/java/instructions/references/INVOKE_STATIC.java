package instructions.references;

import instructions.base.Index16Instruction;
import instructions.base.MethodInvokeLogic;
import rtda.methodarea.Clinit;
import rtda.stack.StackFrame_;
import rtda.methodarea.rtcp.symref.MethodRef;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

// 调用静态方法指令
public class INVOKE_STATIC extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        MethodRef methodRef = (MethodRef) (cp.getConstant(index).getVal());
        Method_ resolvedMethod = methodRef.resolvedMethod();
        if (!resolvedMethod.isStatic()) {
            throw new IncompatibleClassChangeError("调用方法非静态方法！");
        }

        // 类初始化
        if (!frame.getMethod_().getInstanceKlass_().getClinitFlag()
//                && frame.getMethod_().getInstanceKlass_().getStaticSlotCount() != 0
        ) {
            Clinit.revertNextPc(frame);
            Clinit.clinitClass(frame.getThread_(), frame.getMethod_().getInstanceKlass_());
            return;
        }

        MethodInvokeLogic.invokeMethod(frame, resolvedMethod);
    }
}
