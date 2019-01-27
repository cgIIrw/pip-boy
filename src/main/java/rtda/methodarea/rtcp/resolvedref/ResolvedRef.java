package rtda.methodarea.rtcp.resolvedref;

import rtda.methodarea.Class_;
import rtda.methodarea.Field_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.rtcp.symref.InterfaceMethodRef;
import rtda.methodarea.rtcp.symref.MethodRef;

public class ResolvedRef {

    // d是加载的当前.class文件的Class对象，c是通过类符号引用的
    // 完全限定名产生的class对象，d应该拥有c的访问权限
    public static Class_ resolvedClassRef(String className, RuntimeConstantPool_ runtimeConstantPool) {
        Class_ d = runtimeConstantPool.getClass_();
        Class_ c = d.getLoader().loadClass(className);
        if (!c.isAccessibleTo(d)) {
            throw new IllegalAccessError("错误的访问权限！");
        }
        return c;
    }

    // 解析方法的符号引用
    public static Method_ resolveMethodRef(MethodRef methodRef) {

        // 当前代码所处的类d
        Class_ d = methodRef.getRuntimeConstantPool().getClass_();
        // 要解析的非接口方法所属的类或接口c
        Class_ c = methodRef.getClass_();
        if (c == null) {
            c = ResolvedRef.resolvedClassRef(methodRef.getClassName(), methodRef.getRuntimeConstantPool());
        }

        // 判断c是否是接口，如果是则抛出IncompatibleClassChangeError异常
        if (c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        // 注释见lookupMethod实现
        Method_ method = methodRef.lookupMethod(c, methodRef.getName(), methodRef.getDescriptor());

        // 执行到这一步如果仍然没有查找到符合条件的方法，则抛出NoSuchMethodError异常
        if (method == null) {
            throw new NoSuchMethodError("出现错误，没有该方法！");
        }

        // 查找到了方法，返回了直接引用，还要在这一步进行权限验证，当前类应该能访问查找到的方法，
        // 否则抛出IllegalAccessError异常
        if (!method.isAccessibleTo(d)) {
            throw new IllegalAccessError();
        }
        return method;
    }

    // 解析字段的符号引用
    public static Field_ resolveFieldRef(FieldRef fieldRef) {
        Class_ d = fieldRef.getRuntimeConstantPool().getClass_();
        Class_ c = fieldRef.getClass_();
        if (c == null) {
            c = ResolvedRef.resolvedClassRef(fieldRef.getClassName(), fieldRef.getRuntimeConstantPool());
        }
        Field_ field = fieldRef.lookupField(c);

        if (field == null) {
            throw new NoSuchFieldError("没有该字段！");
        }

        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError("非法的访问权限！");
        }
        return field;
    }

    // 解析接口方法的符号引用
    public static Method_ resolvedInterfaceMethodRef(InterfaceMethodRef interfaceMethodRef) {

        // 当前代码所处的类d
        Class_ d = interfaceMethodRef.getRuntimeConstantPool().getClass_();

        // 要解析的非接口方法所属的类或接口c
        Class_ c = interfaceMethodRef.getClass_();
        if (c == null) {
            c = ResolvedRef.resolvedClassRef(interfaceMethodRef.getClassName(), interfaceMethodRef.getRuntimeConstantPool());
        }

        // 判断c是否是接口，如果不是则抛出IncompatibleClassChangeError异常
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method_ method = interfaceMethodRef.lookupInterfaceMethod(c, interfaceMethodRef.getName(), interfaceMethodRef.getDescriptor());

        // 执行到这一步如果仍然没有查找到符合条件的方法，则抛出NoSuchMethodError异常
        if (method == null) {
            throw new NoSuchMethodError();
        }

        // 接口中所有方法默认都是public，不存在访问权限问题
//        if (!method.isAccessibleTo(d)) {
//            throw new IllegalAccessError();
//        }

        return method;
    }
}
