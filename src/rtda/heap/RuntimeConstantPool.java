package rtda.heap;

import classfile.ConstantInfo;
import classfile.ConstantPool;
import classfile.CreateConstantInfo;
import classfile.constantInfos.*;

import java.util.NoSuchElementException;

public class RuntimeConstantPool {
    Myclass myclass;
    Object[] constants;


    public RuntimeConstantPool(Myclass myclass, ConstantPool cfCp) {
        this.myclass = myclass;
        ConstantInfo[] constantInfos = cfCp.getCp();
        constants = new Constant[constantInfos.length];
        for (int i = 1; i < constantInfos.length; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.getId()) {
                case CreateConstantInfo.CONSTANT_Integer:
                    constants[i] = ((ConstantIntegerInfo)constantInfo).getValue();
                    break;
                case CreateConstantInfo.CONSTANT_Float:
                    constants[i] = ((ConstantFloatInfo)constantInfo).getValue();
                    break;
                case CreateConstantInfo.CONSTANT_Long:
                    constants[i] = ((ConstantLongInfo)constantInfo).getValue();
                    i++;
                    break;
                case CreateConstantInfo.CONSTANT_Double:
                    constants[i] = ((ConstantDoubleInfo)constantInfo).getValue();
                    i++;
                    break;
                case CreateConstantInfo.CONSTANT_String:
                    constants[i] = ((ConstantStringInfo)constantInfo).string();
                    break;
                case CreateConstantInfo.CONSTANT_Class:



            }
        }




    }

    public Object getConstants(int id) {
        Object c = this.constants[id];
        if (c != null) {
            return c;
        } else {
            throw new NoSuchElementException("No constants at index" + id);
        }
    }
}
