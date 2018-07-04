package rtda.heap;

import classfile.ConstantInfo;
import classfile.ConstantPool;
import classfile.CreateConstantInfo;
import classfile.constantInfos.*;

import java.util.NoSuchElementException;

public class RuntimeConstantPool {
    Myclass myclass;
    Constant[] constants;


    public RuntimeConstantPool(Myclass myclass, ConstantPool cfCp) {
        this.myclass = myclass;
        ConstantInfo[] constantInfos = cfCp.getCp();
        constants = new Constant[constantInfos.length];
        for (int i = 1; i < constantInfos.length; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.getId()) {
                case CreateConstantInfo.CONSTANT_Integer:
                    constants[i].setVal(((ConstantIntegerInfo)constantInfo).getValue());
                    constants[i].setType(3);
                    break;
                case CreateConstantInfo.CONSTANT_Float:
                    constants[i].setVal(((ConstantFloatInfo)constantInfo).getValue());
                    constants[i].setType(4);
                    break;
                case CreateConstantInfo.CONSTANT_Long:
                    constants[i].setVal(((ConstantLongInfo)constantInfo).getValue());
                    constants[i].setType(5);
                    i++;
                    break;
                case CreateConstantInfo.CONSTANT_Double:
                    constants[i].setVal(((ConstantDoubleInfo)constantInfo).getValue());
                    constants[i].setType(6);
                    i++;
                    break;
                case CreateConstantInfo.CONSTANT_String:
                    constants[i].setVal(((ConstantStringInfo)constantInfo).string());
                    constants[i].setType(8);
                    break;
                case CreateConstantInfo.CONSTANT_Class:
                    constants[i].setVal(new ClassRef(this, (ConstantClassInfo)constantInfo));
                    constants[i].setType(7);
                    break;
                case CreateConstantInfo.CONSTANT_Fieldref:
                    constants[i].setVal(new FieldRef(this, (ConstantFieldrefInfo)constantInfo));
                    constants[i].setType(9);
                    break;
                case CreateConstantInfo.CONSTANT_Methodref:
                    constants[i].setVal(new MethodRef(this,(ConstantMethodrefInfo)constantInfo));
                    constants[i].setType(10);
                    break;
                case CreateConstantInfo.CONSTANT_InterfaceMethodref:
                    constants[i].setVal(new InterfaceMethodRef(this, (ConstantInterfaceMethodrefInfo)constantInfo));
                    constants[i].setType(11);
                    break;
                default:
                    break;
                    // todo
            }
        }
    }

    public Constant getConstants(int id) {
        Constant c = this.constants[id];
        if (c != null) {
            return c;
        } else {
            throw new NoSuchElementException("No constants at index" + id);
        }
    }
}
