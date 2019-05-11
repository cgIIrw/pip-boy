package native_.java.lang;

import native_.JNINativeMethod;
import native_.JVM_ENTRY.*;
import native_.Registry;

public class JClass {

    private static JNINativeMethod[] methods = {
            new JNINativeMethod("getName0",
                    "()Ljava/lang/String;", new JVM_GetClassName()),
    };

    // 传入className，对本地方法进行注册
    public static void Java_java_lang_Class_registerNatives(String className) {
        for (JNINativeMethod jniNativeMethod : methods) {
            Registry.registerMethod(className, jniNativeMethod.getMethodName(),
                    jniNativeMethod.getMethodDescriptor(), jniNativeMethod.getNativeMethod());
        }
    }
}
