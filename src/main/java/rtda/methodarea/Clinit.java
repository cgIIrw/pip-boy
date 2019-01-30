package rtda.methodarea;

import rtda.stack.StackFrame_;
import rtda.stack.Thread_;

// 何时开始类加载没有进行强制约束，但是对于初始化阶段虚拟机
// 规范进行了严格规定：
// new、getstatic、putstatic、invokestatic四条指令之前；
// 对类进行反射调用前；
// 初始化一个类之前，父类必须先初始化；
// 执行的主类需要先初始化；
// 使用JDK1.7动态语言支持时的某些场景；
public class Clinit {
    // 类初始化方法
    public static void clinitClass(Thread_ thread, Class_ class_) {
        getClinitMethod(thread, class_);
        clinitSuperClass(thread, class_);
        class_.setClinitedFlag(true);
    }

    // 获取<clinit>方法，一旦获取到就生成栈帧压入虚拟机栈中
    private static void getClinitMethod(Thread_ thread, Class_ class_) {
        Method_ clinit = class_.getStaticMethod("<clinit>", "()V");
        // 从当前的类查找类初始化器，如果不存在就要考虑在父类中查找了，
        // 后者相当于递归调用，将在其他方法中实现
        if (clinit != null) {
            StackFrame_ clinitFrame = new StackFrame_(thread, clinit);
            thread.pushStackFrame_(clinitFrame);
        }
    }

    // 在父类中递归查找和获取<clinit>方法
    private static void clinitSuperClass(Thread_ thread, Class_ class_) {
        if (!class_.isInterface()) {
            Class_ superClass = class_.getSuperClass();
            if (superClass != null && !superClass.getClinitFlag())
                clinitClass(thread, superClass);
        }
    }

//    // 修改frame中的nextPC(用以重置线程pc)
//    public static void revertNextPc(StackFrame_ frame) {
//        frame.setNextPC(frame.getThread_().getPc());
//    }
}
