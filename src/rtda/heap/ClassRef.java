package rtda.heap;

import classfile.constantInfos.ConstantClassInfo;


// 类或接口的符号引用
public class ClassRef extends SymRef {

    public ClassRef(RuntimeConstantPool runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        setClassName(classInfo.getName());
    }
}
