package rtda.heap;

import classfile.constantInfos.ConstantClassInfo;

public class ClassRef extends SymRef {

    public ClassRef(RuntimeConstantPool runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        this.className = classInfo.getName();
    }
}
