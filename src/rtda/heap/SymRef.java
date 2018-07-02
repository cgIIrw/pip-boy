package rtda.heap;

public class SymRef {
    RuntimeConstantPool runtimeConstantPool;
    String className;

    // 符号引用的真正类，解析阶段将符号引用替换成直接引用
    Myclass myclass;

    public SymRef(RuntimeConstantPool runtimeConstantPool) {
        this.runtimeConstantPool = runtimeConstantPool;
    }

    // todo
}
