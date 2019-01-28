package native_;

import rtda.heap.Instance_;
import rtda.stack.StackFrame_;

public interface JVM_ENTRY {

    class JVM_IHashCode implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {
            Instance_ thisRef = frame.getLocalVars().getRef(0);
            int hashCode = thisRef.hashCode();
            frame.getOperandStack().pushInt(hashCode);
        }
    }

    class JVM_MonitorWait implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {

        }
    }

    class JVM_MonitorNotify implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {

        }
    }

    class JVM_MonitorNotifyAll implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {

        }
    }

    class JVM_Clone implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {

        }
    }
}
