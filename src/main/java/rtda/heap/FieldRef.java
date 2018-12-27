package rtda.heap;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantFieldrefInfo;

public class FieldRef extends MemberRef{
    private MyField myField;


    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldrefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        setClassName(((ConstantFieldrefInfo)info).getClassName());
        setName(((ConstantFieldrefInfo)info).getNameAndDescriptor()[0]);
        setDescriptor(((ConstantFieldrefInfo)info).getNameAndDescriptor()[1]);
    }

    public MyField resolvedField() {
        if (myField == null) {
            resolveFieldRef();
        }
        return myField;
    }

    public void resolveFieldRef() {
        Class_ d = this.getRuntimeConstantPool().getClass_();
        Class_ c = resolvedClass();
        MyField field = lookupField(c, getName(), getDescriptor());

        if (field == null) {
            throw new NoSuchFieldError("java.lang.NoSuchFieldError");
        }

        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.myField = field;
    }



    public MyField lookupField(Class_ class_, String name, String descriptor) {
        for (MyField field : class_.getFields()) {
            if (this.getName().equals(field.getName()) && this.getDescriptor().equals(field.getDescriptor())) {
                return field;
            }
        }

        for (Class_ iface : class_.getInterfaces()) {
            MyField field = lookupField(iface, name, descriptor);
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
