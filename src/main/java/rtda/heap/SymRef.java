package rtda.heap;

public class SymRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;

    // 符号引用的真正类，解析阶段将符号引用替换成直接引用
    private Class_ class_;

    public SymRef(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    // todo

    public Class_ resolvedClass() {
        if (class_ == null) {
            resolvedClassRef();
        }
        return class_;
    }

    public void resolvedClassRef() {
        Class_ d = this.runtimeConstantPool.getClass_();
        Class_ c = d.getLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.class_ = c;
    }

    public RuntimeConstantPool getRuntimeConstantPool() {
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
