package instructions.natived;

import instructions.base.NoOperandsInstruction;
import native_.NativeMethod;
import native_.Registry;
import native_.java.lang.JClass;
import native_.java.lang.JRuntime;
import rtda.methodarea.Method_;
import rtda.stack.StackFrame_;

public class INVOKE_NATIVE extends NoOperandsInstruction {
    @Override
    public void execute(StackFrame_ frame) {
        Method_ method = frame.getMethod_();
        String className = method.getInstanceKlass_().getThisClassName();
        String methodName = method.getName();
        String methodDescriptor = method.getDescriptor();
        if (className.equals("java/lang/Class"))
            JClass.Java_java_lang_Class_registerNatives(className);
        if (className.equals("java/lang/Runtime"))
            JRuntime.Java_java_lang_Runtime_registerNatives(className);
        // 查找相应的本地方法
        NativeMethod nativeMethod = Registry.findNativeMethod(className, methodName, methodDescriptor);
        // 如果返回的是null，那么抛出一个带有信息的运行时异常
        if (nativeMethod == null) {
            String methodInfo = className + "." + methodName + " " + methodDescriptor;
            throw new RuntimeException(methodInfo);
        }
        // 执行本地方法
        nativeMethod.execute(frame);
    }
}
