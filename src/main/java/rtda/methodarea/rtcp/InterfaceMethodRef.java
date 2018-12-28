package rtda.methodarea.rtcp;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantInterfaceMethodrefInfo;
import rtda.heap.MethodLookup;
import rtda.methodarea.Class_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.MemberRef;
import rtda.methodarea.rtcp.RuntimeConstantPool_;

/**
 * 接口方法引用类，并提供解析方法
 */
public class InterfaceMethodRef extends MemberRef {
    private Method_ method;


    public InterfaceMethodRef(RuntimeConstantPool_ runtimeConstantPool, ConstantInterfaceMethodrefInfo refInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(refInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantInterfaceMethodrefInfo)info).getClassName());
        setName(((ConstantInterfaceMethodrefInfo)info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantInterfaceMethodrefInfo)info).getNameAndDescriptor()[1]);
    }

    public Method_ resolvedInterfaceMethod() {

        // 判断是否缓存有已经解析过的方法，没有则进行解析
        if (this.method == null) {
            resolvedInterfaceMethodRef();
        }
        return this.method;
    }

    public void resolvedInterfaceMethodRef() {

        // 当前代码所处的类d
        Class_ d = this.getRuntimeConstantPool().getClass_();

        // 要解析的非接口方法所属的类或接口c
        Class_ c = this.resolvedClass();

        // 判断c是否是接口，如果不是则抛出IncompatibleClassChangeError异常
        if (!c.isInterface()) {
            throw new IncompatibleClassChangeError();
        }

        Method_ method = lookupInterfaceMethod(c, this.getName(), this.getDescriptor());

        // 执行到这一步如果仍然没有查找到符合条件的方法，则抛出NoSuchMethodError异常
        if (method == null) {
            throw new NoSuchMethodError();
        }

        // 接口中所有方法默认都是public，不存在访问权限问题
//        if (!method.isAccessibleTo(d)) {
//            throw new IllegalAccessError();
//        }

        this.method = method;
    }

    public Method_ lookupInterfaceMethod(Class_ iface, String name, String descriptor) {

        // 在接口中查找是否有简单名和描述符都与目标相匹配的方法，如果有，返回这个方法的直接引用，查找结束
        for (Method_ method : iface.getMethods()) {
            if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                return method;
            }
        }

        // 对接口的父接口递归查找简单名和描述符都与目标相匹配的方法，没查找到返回null
        return MethodLookup.lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
    }
}
