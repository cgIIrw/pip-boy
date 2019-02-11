package rtda.methodarea.rtcp.symref;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantMethodrefInfo;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Method_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;

/**
 * 普通方法引用类，并提供解析方法
 */
public class MethodRef extends MemberRef {
    private Method_ method;

    public MethodRef(RuntimeConstantPool_ runtimeConstantPool, ConstantMethodrefInfo methodrefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(methodrefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantMethodrefInfo) info).getClassName());
        setName(((ConstantMethodrefInfo) info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantMethodrefInfo) info).getNameAndDescriptor()[1]);
    }

    public Method_ resolvedMethod() {

        // 判断是否缓存有已经解析过的方法，没有则进行解析
        if (this.method == null) {
            this.method = ResolvedRef.resolveMethodRef(this);
        }
        return this.method;
    }

    public Method_ lookupMethod(InstanceKlass_ instanceKlass_, String name, String descriptor) {
        Method_ method;

        // 在类class_和它的父类中递归查找是否有简单名和描述符都与目标相匹配的方法，
        // 如果有，返回这个方法的直接引用，查找结束
        method = MethodLookup.lookupMethodInClass(instanceKlass_, name, descriptor);
        if (method != null) {
            return method;
        }

        // 在类class_实现的接口列表及它们的父接口中递归查找是否有简单名和描述符都与目标相匹配的方法
        method = MethodLookup.lookupMethodInInterfaces(instanceKlass_.getInterfaces(), name, descriptor);

        // 如果存在匹配的方法，说明类Class_是一个抽象类，查找结束，抛出AbstractMethodError异常
        if (method != null) {
            throw new AbstractMethodError();
        }
        return method;
    }
}
