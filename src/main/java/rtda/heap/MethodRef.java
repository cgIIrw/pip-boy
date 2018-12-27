package rtda.heap;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantMethodrefInfo;

/**
 * 普通方法引用类，并提供解析方法
 */
public class MethodRef extends MemberRef {
    private MyMethod method;

    public MethodRef(RuntimeConstantPool runtimeConstantPool, ConstantMethodrefInfo methodrefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(methodrefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantMethodrefInfo) info).getClassName());
        setName(((ConstantMethodrefInfo) info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantMethodrefInfo) info).getNameAndDescriptor()[1]);
    }

    public MyMethod resolvedMethod() {

        // 判断是否缓存有已经解析过的方法，没有则进行解析
        if (this.method == null) {
            resolveMethodRef();
        }
        return this.method;
    }

    public void resolveMethodRef() {

        // 当前代码所处的类d
        Class_ d = this.getRuntimeConstantPool().getClass_();

        // 要解析的非接口方法所属的类或接口c
        Class_ c = this.resolvedClass();

        // 判断c是否是接口，如果是则抛出IncompatibleClassChangeError异常
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        // 注释见lookupMethod实现
        MyMethod method = lookupMethod(c, this.getName(), this.getDescriptor());

        // 执行到这一步如果仍然没有查找到符合条件的方法，则抛出NoSuchMethodError异常
        if (method == null) {
            throw new NoSuchMethodError();
        }

        // 查找到了方法，返回了直接引用，还要在这一步进行权限验证，当前类应该能访问查找到的方法，
        // 否则抛出IllegalAccessError异常
        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }

        this.method = method;
    }

    public MyMethod lookupMethod(Class_ class_, String name, String descriptor) {
        MyMethod method = null;

        // 在类myclass和它的父类中递归查找是否有简单名和描述符都与目标相匹配的方法，
        // 如果有，返回这个方法的直接引用，查找结束
        method = MethodLookup.lookupMethodInClass(class_, name, descriptor);
        if (method != null) {
            return method;
        }

        // 在类myclass实现的接口列表及它们的父接口中递归查找是否有简单名和描述符都与目标相匹配的方法
        method = MethodLookup.lookupMethodInInterfaces(class_.getInterfaces(), name, descriptor);

        // 如果存在匹配的方法，说明类myclass是一个抽象类，查找结束，抛出AbstractMethodError异常
        if (method != null) {
            throw new AbstractMethodError();
        }
        return method;
    }
}
