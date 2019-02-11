package rtda.methodarea.rtcp.symref;

import classfile.constantpool.constantInfos.ConstantClassInfo;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;


// 类或接口的符号引用
public class ClassRef extends SymRef {
//    private InstanceKlass_ class_;

    public ClassRef(RuntimeConstantPool_ runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        setClassName(classInfo.getName());
    }

    // 解析并进行缓存
    public InstanceKlass_ resolvedClass() {
        if (this.getInstanceKlass_() == null) {
            this.setInstanceKlass_(ResolvedRef.resolvedClassRef(this.getClassName(), this.getRuntimeConstantPool()));
        }
        return getInstanceKlass_();
    }
}
