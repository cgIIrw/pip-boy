package rtda.heap;

public class SymRef {
    private RuntimeConstantPool runtimeConstantPool;
    private String className;

    // 符号引用的真正类，解析阶段将符号引用替换成直接引用
    private Myclass myclass;

    public SymRef(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    // todo

    public Myclass resolvedClass() {
        if (myclass == null) {
            resolvedClassRef();
        }
        return myclass;
    }

    public void resolvedClassRef() {
        Myclass d = this.runtimeConstantPool.getMyclass();
        Myclass c = d.getLoader().loadClass(this.className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.myclass = c;
    }

    public RuntimeConstantPool getRuntimeConstantPool() {
        return runtimeConstantPool;
    }

    public String getClassName() {
        return className;
    }

    public Myclass getMyclass() {
        return myclass;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
