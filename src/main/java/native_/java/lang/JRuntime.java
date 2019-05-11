package native_.java.lang;

import native_.JNINativeMethod;
import native_.JVM_ENTRY;
import native_.Registry;

/**
 * @Author: cgrw
 */
public class JRuntime {
    private static JNINativeMethod[] methods = {
            new JNINativeMethod("gc", "()V", new JVM_ENTRY.JVM_GC()),
    };

    public static void Java_java_lang_Runtime_registerNatives(String className) {
        for (JNINativeMethod jniNativeMethod : methods) {
            Registry.registerMethod(className, jniNativeMethod.getMethodName(),
                    jniNativeMethod.getMethodDescriptor(), jniNativeMethod.getNativeMethod());
        }
    }
}
