package native_;

import rtda.heap.Instance_;
import rtda.heap.StringPool;
import rtda.methodarea.InstanceKlass_;
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

    class GetObjectClass implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {
            Instance_ thisRef = frame.getLocalVars().getRef(0);
            Instance_ jClass = thisRef.getInstanceKlass_().getJava_mirror_();
            frame.getOperandStack().pushRef(jClass);
        }
    }

    class JVM_GetClassName implements NativeMethod {

        @Override
        public void execute(StackFrame_ frame) {
            Instance_ thisRef = frame.getLocalVars().getRef(0);
            InstanceKlass_ instanceKlass = thisRef.getKlass();
            String name = instanceKlass.getThisClassName();
            name = name.replace("/", ".");
            Instance_ jName = StringPool.jString(instanceKlass.getLoader(), name);
            frame.getOperandStack().pushRef(jName);
        }
    }
}
