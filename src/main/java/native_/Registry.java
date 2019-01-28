package native_;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                String[] temp = className.split("/");
                String string = "";
                for (int i = 0; i < temp.length - 1; i++) {
                    string = string + temp[i] + ".";
                }

                // 将原本className的最后的简单名前添加一个“J”，在className前添加“native_.”
                String name = "native_." + string + "J" + temp[temp.length - 1];

                try {
                    Class<?> c = Class.forName(name);
                    for (Method method : c.getMethods()) {
                        // 方法名以"registerNatives"结尾时，为要寻找的方法
                        if (method.getName().endsWith("registerNatives"))
                            // 静态方法，所以第一个参数为null
                            method.invoke(null, className);
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                // do nothing
//                System.out.println(frame.getMethod_().getClass_().getThisClassName());
            };
        }
        return null;
    }
}
