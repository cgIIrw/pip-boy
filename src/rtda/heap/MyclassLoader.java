package rtda.heap;

import classfile.ClassFile;
import classpath.ClassPath;
import rtda.LocalVars;

import java.io.IOException;
import java.util.HashMap;

/*
class names:
    - primitive types: boolean, byte, int ...
    - primitive arrays: [Z, [B, [I ...
    - non-array classes: java/lang/Object ...
    - array classes: [Ljava/lang/Object; ...
*/
public class MyclassLoader {
    ClassPath cp;
    HashMap<String, Myclass> classMap;

    public MyclassLoader(ClassPath cp) {
        this.cp = cp;
        classMap = new HashMap<>();

    }

    public Myclass loadClass(String name) {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        }
        // 数组类的数据不来自Class文件，运行时生成，要单独考虑
        return loadNonArrayClass(name);
    }

    public Myclass loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        Myclass myclass = defineClass(data);
        link(myclass);
        return myclass;
    }

    public byte[] readClass(String name) {
        byte[] data = new byte[0];
        try {
           data = this.cp.readClass(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Myclass defineClass(byte[] bytes) {
        // 这里就已经完成了字节码到Myclass对象的转换，基本思路就是一个"空"架子，往
        // 里面填东西，并没有什么黑科技
        Myclass myclass = parseClass(bytes);
        // 相当于标记一下是谁加载的这个类(当前的类加载器加载的类)
        myclass.setLoader(this);
        // 和对象不一样，子类的Class是需要加载父类的Class的
        resolveSuperClass(myclass);
        resolveInterfaces(myclass);
        this.classMap.put(myclass.getName(), myclass);
        return myclass;
    }

    public Myclass parseClass(byte[] bytes) {
        ClassFile cf = ClassFile.Parse(bytes);
        return new Myclass(cf);
    }

    public void resolveSuperClass(Myclass myclass) {
        if (!myclass.getName().equals("java/lang/Object")) {
            myclass.setSuperClass(myclass.getLoader().loadClass(myclass.getSuperClassName()));
        }
    }

    public void resolveInterfaces(Myclass myclass) {
        int interCount = myclass.getInterfaceNames().length;
        if (interCount > 0) {
            myclass.setInterfaces(new Myclass[interCount]);
            for (int i = 0; i < interCount; i++) {
                myclass.getInterfaces()[i] = myclass.getLoader()
                        .loadClass(myclass.getInterfaceNames()[i]);
            }
        }
    }

    public void link(Myclass myclass) {
        // 验证
        vertify(myclass);
        // 准备：在方法区中为变量分配内存，这里的变量指static修饰。
        prepare(myclass);
    }

    public void vertify(Myclass myclass) {
        // todo
    }

    public void prepare(Myclass myclass) {
        // todo
        // 计算实例字段的个数
        calcInstanceFieldSlotIds(myclass);
        // 计算静态字段的个数
        calcStaticFieldSlotIds(myclass);
        // 给类变量分配空间，然后给他们赋予初始值
        allocAndInitStaticVars(myclass);
    }

    public void calcInstanceFieldSlotIds(Myclass myclass) {
        int slotId = 0;
        if (myclass.getSuperClass() != null) {
            // 从上往下，先把父类的个数加上
            slotId = myclass.getSuperClass().getInstanceSlotCount();
        }

        for (int i = 0; i < myclass.getFields().length; i++) {
            if (!myclass.getFields()[i].isStatic()) {
                myclass.getFields()[i].slotId = slotId;
                slotId++;
                if (myclass.getFields()[i].isLongOrDouble()) {
                    slotId++;
                }
            }
        }
       myclass.setInstanceSlotCount(slotId);
    }

    public void calcStaticFieldSlotIds(Myclass myclass) {
        int slotId = 0;
        for (int i = 0; i < myclass.getFields().length; i++) {
            if (myclass.getFields()[i].isStatic()) {
                myclass.getFields()[i].slotId = slotId;
                slotId++;
                if (myclass.getFields()[i].isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        myclass.setStaticSlotCount(slotId);
    }

    public void allocAndInitStaticVars(Myclass myclass) {
        // LocalVars是一个Slot[]的包装类，这里用来定位和初始化
        myclass.staticVars = new LocalVars(myclass.getStaticSlotCount());
        for (MyField myField : myclass.getFields()) {
            if (myField.isStatic() && myField.isFinal()) {
                initStaticFinalVar(myclass, myField);
            }
        }
    }

    public void initStaticFinalVar(Myclass myclass, MyField myField) {
        LocalVars vars = myclass.staticVars;
        RuntimeConstantPool cp = myclass.getRuntimeConstantPool();
        // 在此强调一下，这里constValue_index的是static final
        int cpIndex = myField.constValue_index;
        int slotId = myField.slotId;

        // 《深入》P177，这个final static根据不同类型，获取相应类型的值
        if (cpIndex > 0) {
            switch (myField.descriptor) {
                // 非long double float类型统统转化为int存储
                case "Z" :
                case "B" :
                case "C" :
                case "S" :
                case "I" :
                    int val = (int)(cp.getConstant(cpIndex).getVal());
                    vars.setInt(slotId, val);
                    break;
                case "J" :
                    long lval = (long)(cp.getConstant(cpIndex).getVal());
                    vars.setLong(slotId, lval);
                    break;
                case "F" :
                    float fval = (float)(cp.getConstant(cpIndex).getVal());
                    vars.setFloat(slotId, fval);
                    break;
                case "D" :
                    double dval = (double)(cp.getConstant(cpIndex).getVal());
                    vars.setDouble(slotId, dval);
                    break;
                case "Ljava/lang/String;" :
                    // todo
                    break;
                default:
                    break;
            }
        }
    }
}
