package native_;

import rtda.stack.StackFrame_;

// 本地方法公共接口
public interface NativeMethod {
    void execute(StackFrame_ frame);
}
