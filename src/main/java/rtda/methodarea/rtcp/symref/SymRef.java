package rtda.methodarea.rtcp.symref;

import rtda.methodarea.Class_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

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

    // 解析并进行缓存
    public Class_ resolvedClass() {
        if (class_ == null) {
            resolvedClassRef();
        }
        return class_;
    }

    // d是加载的当前.class文件的Class对象，c是通过类符号引用的
    // 完全限定名产生的class对象，d应该拥有c的访问权限
    public void resolvedClassRef() {
        Class_ d = this.runtimeConstantPool.getClass_();
        Class_ c = d.getLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError("错误的访问权限！");
        }
        this.class_ = c;
    }

    public RuntimeConstantPool_ getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public String getClassName() {
        return className;
    }

    public Class_ getClass_() {
        return class_;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
