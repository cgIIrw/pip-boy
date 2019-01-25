package native_;

import java.util.HashMap;

public class Registry {

    private static HashMap<String, NativeMethod> register = new HashMap<>();

    // 一一映射
    public static void registerMethod(String className, String methodName, String methodDescriptor, NativeMethod nativeMethod) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        register.put(key, nativeMethod);
    }

    public static NativeMethod findNativeMethod(String className, String methodName, String methodDescriptor) {
        String key = className + "~" + methodName + "~" + methodDescriptor;
        if (register.containsKey(key)) {
            return register.get(key);
        }
        if (methodDescriptor.equals("()V") && methodName.equals("registerNatives")) {
            return frame -> {
                // do nothing
            };
        }
        return null;
    }
}
