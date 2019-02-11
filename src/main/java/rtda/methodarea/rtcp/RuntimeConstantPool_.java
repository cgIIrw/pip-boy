package rtda.methodarea.rtcp;

import classfile.constantpool.ConstantInfo;
import classfile.constantpool.ConstantInfoFactory;
import classfile.constantpool.ConstantPool;
import classfile.constantpool.constantInfos.*;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.rtcp.symref.ClassRef;
import rtda.methodarea.rtcp.symref.FieldRef;
import rtda.methodarea.rtcp.symref.InterfaceMethodRef;
import rtda.methodarea.rtcp.symref.MethodRef;

import java.util.NoSuchElementException;

public class RuntimeConstantPool_ {
    private InstanceKlass_ instanceKlass_;
    private Constant[] constants;

    public RuntimeConstantPool_(InstanceKlass_ instanceKlass_, ConstantPool cp) {
        this.instanceKlass_ = instanceKlass_;
        ConstantInfo[] constantInfos = cp.getCp();
        constants = new Constant[constantInfos.length];

        // constants需要初始化防止空指针异常
        for (int i = 0; i < constants.length; i++) {
            constants[i] = new Constant();
        }

        // constants的索引对应常量池中的索引，从1开始
        for (int i = 1; i < constantInfos.length; i++) {
            ConstantInfo constantInfo = constantInfos[i];
            switch (constantInfo.getTag()) {
                case ConstantInfoFactory.CONSTANT_Integer:
                    constants[i].setVal(((ConstantIntegerInfo) constantInfo).getValue());
                    constants[i].setType(3);
                    break;
                case ConstantInfoFactory.CONSTANT_Float:
                    constants[i].setVal(((ConstantFloatInfo) constantInfo).getValue());
                    constants[i].setType(4);
                    break;
                case ConstantInfoFactory.CONSTANT_Long:
                    constants[i].setVal(((ConstantLongInfo) constantInfo).getValue());
                    constants[i].setType(5);
                    i++;
                    break;
                case ConstantInfoFactory.CONSTANT_Double:
                    constants[i].setVal(((ConstantDoubleInfo) constantInfo).getValue());
                    constants[i].setType(6);
                    i++;
                    break;
                case ConstantInfoFactory.CONSTANT_String:
                    constants[i].setVal(((ConstantStringInfo) constantInfo).string());
                    constants[i].setType(8);
                    break;
                case ConstantInfoFactory.CONSTANT_Class:
                    constants[i].setVal(new ClassRef(this, (ConstantClassInfo) constantInfo));
                    constants[i].setType(7);
                    break;
                case ConstantInfoFactory.CONSTANT_Fieldref:
                    constants[i].setVal(new FieldRef(this, (ConstantFieldrefInfo) constantInfo));
                    constants[i].setType(9);
                    break;
                case ConstantInfoFactory.CONSTANT_Methodref:
                    constants[i].setVal(new MethodRef(this, (ConstantMethodrefInfo) constantInfo));
                    constants[i].setType(10);
                    break;
                case ConstantInfoFactory.CONSTANT_InterfaceMethodref:
                    constants[i].setVal(new InterfaceMethodRef(this, (ConstantInterfaceMethodrefInfo) constantInfo));
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
            throw new NoSuchElementException("不存在索引：" + id);
        }
    }

    public InstanceKlass_ getInstanceKlass_() {
        return instanceKlass_;
    }

    public Constant[] getConstants() {
        return constants;
    }
}
