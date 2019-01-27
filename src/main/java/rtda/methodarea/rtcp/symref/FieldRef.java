package rtda.methodarea.rtcp.symref;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.constantInfos.ConstantFieldrefInfo;
import rtda.methodarea.Class_;
import rtda.methodarea.Field_;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.methodarea.rtcp.resolvedref.ResolvedRef;

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
        if (this.field == null) {
            this.field = ResolvedRef.resolveFieldRef(this);
        }
        return this.field;
    }

    public Field_ lookupField(Class_ class_) {
        for (Field_ field : class_.getFields()) {
            if (this.getName().equals(field.getName()) && this.getDescriptor().equals(field.getDescriptor())) {
                return field;
            }
        }

        for (Class_ iface : class_.getInterfaces()) {
            Field_ field = lookupField(iface);
            if (field != null) {
                return field;
            }
        }

        if (class_.getSuperClass() != null) {
            return lookupField(class_.getSuperClass());
        }
        return null;
    }
}
