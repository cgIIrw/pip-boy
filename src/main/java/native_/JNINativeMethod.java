package native_;

public class JNINativeMethod {
    private String methodName;
    private String methodDescriptor;
    private NativeMethod nativeMethod;

    public JNINativeMethod(String methodName, String methodDescriptor, NativeMethod nativeMethod) {
        this.methodName = methodName;
        this.methodDescriptor = methodDescriptor;
        this.nativeMethod = nativeMethod;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getMethodDescriptor() {
        return methodDescriptor;
    }

    public NativeMethod getNativeMethod() {
        return nativeMethod;
    }
}
