package native_.java.lang;

import native_.JNINativeMethod;
import native_.JVM_ENTRY.*;
import native_.Registry;

public class JSystem {

    private static JNINativeMethod[] methods = {
            new JNINativeMethod("gc", "()V", new JVM_GC()),
            new JNINativeMethod("currentTimeMillis", "()J", new JVM_CurrentTimeMillis()),
            new JNINativeMethod("nanoTime", "()J", new JVM_NanoTime()),
            new JNINativeMethod("arraycopy", "(\" OBJ \"I\" OBJ \"II)V\"", new JVM_ArrayCopy()),
    };

    public static void Java_java_lang_System_registerNatives(String className) {
        for (JNINativeMethod jniNativeMethod : methods) {
            Registry.registerMethod(className, jniNativeMethod.getMethodName(),
                    jniNativeMethod.getMethodDescriptor(), jniNativeMethod.getNativeMethod());
        }
    }
}
