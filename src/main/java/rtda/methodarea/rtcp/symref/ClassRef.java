package rtda.methodarea.rtcp.symref;

import classfile.constantpool.constantInfos.ConstantClassInfo;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.symref.SymRef;


// 类或接口的符号引用
public class ClassRef extends SymRef {

    public ClassRef(RuntimeConstantPool_ runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        setClassName(classInfo.getName());
    }
}
