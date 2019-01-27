package instructions.natived;

import instructions.base.NoOperandsInstruction;
import native_.NativeMethod;
import native_.Registry;
import rtda.methodarea.Method_;
import rtda.stack.StackFrame_;

public class INVOKE_NATIVE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Method_ method = frame.getMethod_();
        String className = method.getClass_().getThisClassName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        // 查找相应的本地方法
        NativeMethod nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        // 如果返回的是null，那么抛出一个带有信息的运行时异常
        if (nativeMethod == null) {
            String methodInfo = className + "." + methodName + methodDescriptor;
            throw new RuntimeException(methodInfo);
        }
        // 执行本地方法
        nativeMethod.execute(frame);
    }
}
