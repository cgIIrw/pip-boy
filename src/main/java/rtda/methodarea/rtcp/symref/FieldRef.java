package rtda.methodarea.rtcp.symref;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantFieldrefInfo;
import rtda.methodarea.Class_;
import rtda.methodarea.Field_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.symref.MemberRef;

public class FieldRef extends MemberRef {
    private Field_ field;

    public FieldRef(RuntimeConstantPool_ runtimeConstantPool, ConstantFieldrefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantFieldrefInfo)info).getClassName());
        setName(((ConstantFieldrefInfo)info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantFieldrefInfo)info).getNameAndDescriptor()[1]);
    }

    public Field_ resolvedField() {
        if (field == null) {
            resolveFieldRef();
        }
        return field;
    }

    public void resolveFieldRef() {
        Class_ d = this.getRuntimeConstantPool().getClass_();
        Class_ c = resolvedClass();
        Field_ field = lookupField(c, getName(), getDescriptor());

        if (field == null) {
            throw new NoSuchFieldError("java.lang.NoSuchFieldError");
        }

        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.field = field;
    }

    public Field_ lookupField(Class_ class_, String name, String descriptor) {
        for (Field_ field : class_.getFields()) {
            if (this.getName().equals(field.getName()) && this.getDescriptor().equals(field.getDescriptor())) {
                return field;
            }
        }

        for (Class_ iface : class_.getInterfaces()) {
            Field_ field = lookupField(iface, name, descriptor);
            if (field != null) {
                return field;
            }
        }

        if (class_.getSuperClass() != null) {
            return lookupField(class_.getSuperClass(), name, descriptor);
        }
        return null;
    }
}
