package native_.java.lang;

import native_.JNINativeMethod;
import native_.JVM_ENTRY.*;
import native_.Registry;
import rtda.stack.StackFrame_;

public class JObject {
    private static JNINativeMethod[] methods = {
            new JNINativeMethod("hashCode", "()I", new JVM_IHashCode()),
            new JNINativeMethod("wait", "(J)V", new JVM_MonitorWait()),
            new JNINativeMethod("notify", "()V", new JVM_MonitorNotify()),
            new JNINativeMethod("notifyAll", "()V", new JVM_MonitorNotifyAll()),
            new JNINativeMethod("clone", "()Ljava/lang/Object;", new JVM_Clone()),
            new JNINativeMethod("getClass", "()Ljava/lang/Class;", new GetObjectClass()),
    };

    // 传入className，对本地方法进行注册
    public static void Java_java_lang_Object_registerNatives(String className) {
        for (JNINativeMethod jniNativeMethod : methods) {
            Registry.registerMethod(className, jniNativeMethod.getMethodName(),
                    jniNativeMethod.getMethodDescriptor(), jniNativeMethod.getNativeMethod());
        }
    }
//
//    public static void Java_java_lang_Object_getClass(StackFrame_ frame) {
////        Registry.registerMethod(className, "getClass",
////                "()Ljava/lang/Class", new GetObjectClass());
//        new GetObjectClass().execute(frame);
//    }
}
