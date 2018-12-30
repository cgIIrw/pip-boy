package rtda.methodarea.rtcp.symref;

import classfile.constantpool.constantInfos.ConstantClassInfo;
import rtda.methodarea.Class_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;
import rtda.methodarea.rtcp.symref.SymRef;


// 类或接口的符号引用
public class ClassRef extends SymRef {
    private Class_ class_;

    public ClassRef(RuntimeConstantPool_ runtimeConstantPool, ConstantClassInfo classInfo) {
        super(runtimeConstantPool);
        setClassName(classInfo.getName());
    }

    // 解析并进行缓存
    public Class_ resolvedClass() {
        if (this.class_ == null) {
            this.class_ = ResolvedRef.resolvedClassRef(this.getClassName(), this.getRuntimeConstantPool());
        }
        return this.class_;
    }
}
