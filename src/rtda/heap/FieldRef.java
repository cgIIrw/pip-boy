package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantFieldrefInfo;

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
        Myclass d = this.getRuntimeConstantPool().getMyclass();
        Myclass c = resolvedClass();
        MyField field = lookupField(c, getName(), getDescriptor());

        if (field == null) {
            throw new NoSuchFieldError("java.lang.NoSuchFieldError");
        }

        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.myField = field;
    }



    public MyField lookupField(Myclass myclass, String name, String descriptor) {
        for (MyField field : myclass.getFields()) {
            if (this.getName().equals(field.getName()) && this.getDescriptor().equals(field.getDescriptor())) {
                return field;
            }
        }

        for (Myclass iface : myclass.getInterfaces()) {
            MyField field = lookupField(iface, name, descriptor);
            if (field != null) {
                return field;
            }
        }

        if (myclass.getSuperClass() != null) {
            return lookupField(myclass.getSuperClass(), name, descriptor);
        }
        return null;
    }
}
