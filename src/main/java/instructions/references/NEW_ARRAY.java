package instructions.references;

import instructions.base.Index8Instruction;
import rtda.heap.Instance_;
import rtda.methodarea.ClassLoader_;
import rtda.methodarea.Class_;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

// 该命令接受两个操作数，一个是指令后紧接着读取的一个字节表示
// 的int数，代表基本类型，另一个是从操作数栈弹出，代表数组长度
public class NEW_ARRAY extends Index8Instruction {

    // 这些常量表示基本类型
    private static final int AT_BOOLEAN = 4;
    private static final int AT_CHAR = 5;
    private static final int AT_FLOAT = 6;
    private static final int AT_DOUBLE = 7;
    private static final int AT_BYTE = 8;
    private static final int AT_SHORT = 9;
    private static final int AT_INT = 10;
    private static final int AT_LONG = 11;

    @Override
    public void execute(StackFrame_ frame) {
        OperandStack_ operandStack = frame.getOperandStack();
        int count = operandStack.popInt();
        if (count < 0)
            throw new NegativeArraySizeException();

        ClassLoader_ loader = frame.getMethod_().getClass_().getLoader();
        Class_ arrClass = getPrimitiveArrayClass(loader);
        Instance_ arr = arrClass.newArray(count);
        operandStack.pushRef(arr);
    }

    private Class_ getPrimitiveArrayClass(ClassLoader_ loader) {
        switch (this.index) {
            case AT_BOOLEAN:
                return loader.loadClass("[Z");
            case AT_BYTE:
                return loader.loadClass("[B");
            case AT_CHAR:
                return loader.loadClass("[C");
            case AT_SHORT:
                return loader.loadClass("[S");
            case AT_INT:
                return loader.loadClass("[I");
            case AT_LONG:
                return loader.loadClass("[J");
            case AT_FLOAT:
                return loader.loadClass("[F");
            case AT_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new RuntimeException("不存在的类型！");
        }
    }
}
