package rtda.methodarea.rtcp.symref;

import rtda.methodarea.Class_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;

public class SymRef {
    private RuntimeConstantPool_ runtimeConstantPool;

    // 符号引用的完全限定名
    private String className;

    // 符号引用的真正类，解析阶段将符号引用替换成直接引用
    private Class_ class_;

    public SymRef(RuntimeConstantPool_ runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    // todo


    public RuntimeConstantPool_ getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public String getClassName() {
        return className;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClass_(Class_ class_) {
        this.class_ = class_;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
