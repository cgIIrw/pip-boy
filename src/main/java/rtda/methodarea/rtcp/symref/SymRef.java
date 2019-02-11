package rtda.methodarea.rtcp.symref;

import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

public class SymRef {
    private RuntimeConstantPool_ runtimeConstantPool;

    // 符号引用的完全限定名
    private String className;

    // 符号引用的真正类，解析阶段将符号引用替换成直接引用
    private InstanceKlass_ instanceKlass_;

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

    public InstanceKlass_ getInstanceKlass_() {
        return instanceKlass_;
    }

    public void setInstanceKlass_(InstanceKlass_ instanceKlass_) {
        this.instanceKlass_ = instanceKlass_;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
