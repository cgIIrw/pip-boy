package instructions.references;

import instructions.base.Index16Instruction;
import rtda.Myframe;
import rtda.OperandStack;
import rtda.heap.ClassRef;
import rtda.heap.Myclass;
import rtda.heap.Myobject;
import rtda.heap.RuntimeConstantPool;

public class CHECK_CAST extends Index16Instruction {
    @Override
    public void execute(Myframe frame) {
        OperandStack stack = frame.getOperandStack();
        Myobject ref = stack.popRef();
        // 这里push只是为了得到引用
        stack.pushRef(ref);
        if (ref == null) {
            return;
        }

        RuntimeConstantPool cp = frame.getMyMethod().getMyclass().runtimeConstantPool;
        ClassRef classRef = (ClassRef)((cp.getConstants(index)).getVal());
        Myclass myclass = classRef.resolvedClass();
        if (!ref.isInstanceOf(myclass)) {
            throw new ClassCastException("java.lang.ClassCastException");
        }
    }
}
