package rtda.heap;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.ConstantInfoFactory;
import classfile.constantpool.ConstantPool;
import classfile.constantpool.constantInfos.*;

import java.util.NoSuchElementException;

public class RuntimeConstantPool {
    private Myclass myclass;
    private Constant[] constants;


    public RuntimeConstantPool(Myclass myclass, ConstantPool cfCp) {
        this.myclass = myclass;
        ConstantInfo[] constantInfos = cfCp.getCp();
        constants = new Constant[constantInfos.length];
        //
        for (int i = 0; i < constants.length; i++) {
            constants[i] = new Constant();
        }

        for (int i = 1; i < constantInfos.length; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.getTag()) {
                case ConstantInfoFactory.CONSTANT_Integer:
                    constants[i].setVal(((ConstantIntegerInfo)constantInfo).getValue());
                    constants[i].setType(3);
                    break;
                case ConstantInfoFactory.CONSTANT_Float:
                    constants[i].setVal(((ConstantFloatInfo)constantInfo).getValue());
                    constants[i].setType(4);
                    break;
                case ConstantInfoFactory.CONSTANT_Long:
                    constants[i].setVal(((ConstantLongInfo)constantInfo).getValue());
                    constants[i].setType(5);
                    i++;
                    break;
                case ConstantInfoFactory.CONSTANT_Double:
                    constants[i].setVal(((ConstantDoubleInfo)constantInfo).getValue());
                    constants[i].setType(6);
                    i++;
                    break;
                case ConstantInfoFactory.CONSTANT_String:
                    constants[i].setVal(((ConstantStringInfo)constantInfo).string());
                    constants[i].setType(8);
                    break;
                case ConstantInfoFactory.CONSTANT_Class:
                    constants[i].setVal(new ClassRef(this, (ConstantClassInfo)constantInfo));
                    constants[i].setType(7);
                    break;
                case ConstantInfoFactory.CONSTANT_Fieldref:
                    constants[i].setVal(new FieldRef(this, (ConstantFieldrefInfo)constantInfo));
                    constants[i].setType(9);
                    break;
                case ConstantInfoFactory.CONSTANT_Methodref:
                    constants[i].setVal(new MethodRef(this,(ConstantMethodrefInfo)constantInfo));
                    constants[i].setType(10);
                    break;
                case ConstantInfoFactory.CONSTANT_InterfaceMethodref:
                    constants[i].setVal(new InterfaceMethodRef(this, (ConstantInterfaceMethodrefInfo)constantInfo));
                    constants[i].setType(11);
                    break;
                default:
                    break;
                    // todo
            }
        }
    }

    public Constant getConstant(int id) {
        Constant c = this.constants[id];
        if (c != null) {
            return c;
        } else {
            throw new NoSuchElementException("No constants at index" + id);
        }
    }

    public Myclass getMyclass() {
        return myclass;
    }

    public Constant[] getConstants() {
        return constants;
    }
}
