package rtda.heap;

import classfile.ConstantInfo;
import classfile.constantInfos.ConstantFieldrefInfo;

public class FieldRef extends MemberRef{
    MyField myField;


    public FieldRef(RuntimeConstantPool runtimeConstantPool, ConstantFieldrefInfo fieldRefInfo) {
        super(runtimeConstantPool);
        copyMemberRefInfo(fieldRefInfo);
    }

    @Override
    void copyMemberRefInfo(ConstantInfo info) {
        className = ((ConstantFieldrefInfo)info).getClassName();
        name = ((ConstantFieldrefInfo)info).getNameAndDescriptor()[0];
        descriptor = ((ConstantFieldrefInfo)info).getNameAndDescriptor()[1];
    }

    public MyField resolvedField() {
        if (myField == null) {
            resolveFieldRef();
        }
        return myField;
    }

    public void resolveFieldRef() {
        Myclass d = this.runtimeConstantPool.myclass;
        Myclass c = resolvedClass();
        MyField field = lookupField(c, name, descriptor);

        if (field == null) {
            throw new NoSuchFieldError("java.lang.NoSuchFieldError");
        }

        if (!field.isAccessibleTo(d)) {
            throw new IllegalAccessError("java.lang.IllegalAccessError");
        }
        this.myField = field;
    }



    public MyField lookupField(Myclass myclass, String name, String descriptor) {
        for (MyField field : myclass.fields) {
            if (this.name == field.name && this.descriptor == field.descriptor) {
                return field;
            }
        }

        for (Myclass iface : myclass.interfaces) {
            MyField field = lookupField(iface, name, descriptor);
            if (field != null) {
                return field;
            }
        }

        if (myclass.superClass != null) {
            return lookupField(myclass.superClass, name, descriptor);
        }
        return null;
    }
}
