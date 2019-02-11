package instructions.references;

import instructions.base.Index16Instruction;
import rtda.heap.Instance_;
import rtda.methodarea.ClassLoader_;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.stack.OperandStack_;
import rtda.stack.StackFrame_;

// anewarray需要两个操作数，第一个是紧跟anewarray指令的两个字
// 节所表示的运行时常量池索引；另一个是从操作数栈弹出，代表数组长度
public class ANEW_ARRAY extends Index16Instruction {
    @Override
    public void execute(StackFrame_ frame) {
        ClassLoader_ loader = frame.getMethod_().getInstanceKlass_().getLoader();
        OperandStack_ operandStack = frame.getOperandStack();
        int count = operandStack.popInt();
        if (count < 0)
            throw new NegativeArraySizeException();
        RuntimeConstantPool_ cp = frame.getMethod_().getInstanceKlass_().getRuntimeConstantPool();
        // 数组引用类型的符号引用
        ClassRef classRef = (ClassRef) (cp.getConstant(this.index).getVal());
        // 解析出引用类型
        InstanceKlass_ instanceKlass_ = classRef.resolvedClass();
        // 获得数组的全限名
        String arrayClassName = "L" + instanceKlass_.getThisClassName() + ";";
        // 获得Java虚拟机运行时生成的数组类
        InstanceKlass_ arrayClass = loader.loadClass(arrayClassName);
        // 获得该数组开辟的空间
        Instance_ arr = arrayClass.newArray(count);
        operandStack.pushRef(arr);
    }
}
