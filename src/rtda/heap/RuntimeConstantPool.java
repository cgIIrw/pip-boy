package rtda.heap;

import classfile.ConstantInfo;
import classfile.ConstantPool;
import classfile.CreateConstantInfo;
import classfile.constantInfos.ConstantIntegerInfo;

public class RuntimeConstantPool {
    Myclass myclass;
    Object[] constants;


    public RuntimeConstantPool(Myclass myclass, ConstantPool cfCp) {
        ConstantIntegerInfo constantIntegerInfo = new ConstantIntegerInfo();
        this.myclass = myclass;
        ConstantInfo[] constantInfos = cfCp.getCp();
        constants = new Constant[constantInfos.length];
        for (int i = 1; i < constantInfos.length; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo) {

            }
        }




    }


}
