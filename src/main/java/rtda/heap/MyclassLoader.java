package rtda.heap;

import classfile.ClassFile;
import classpath.ClassPath;
import rtda.stack.LocalVars_;

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
    HashMap<String, Class_> classMap;

    public MyclassLoader(ClassPath cp) {
        this.cp = cp;
        classMap = new HashMap<>();

    }

    public Class_ loadClass(String name) {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        }
        // 数组类的数据不来自Class文件，运行时生成，要单独考虑
        return loadNonArrayClass(name);
    }

    public Class_ loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        Class_ class_ = defineClass(data);
        link(class_);
        return class_;
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

    public Class_ defineClass(byte[] bytes) {
        // 这里就已经完成了字节码到Myclass对象的转换，基本思路就是一个"空"架子，往
        // 里面填东西，并没有什么黑科技
        Class_ class_ = parseClass(bytes);
        // 相当于标记一下是谁加载的这个类(当前的类加载器加载的类)
        class_.setLoader(this);
        // 和对象不一样，子类的Class是需要加载父类的Class的
        resolveSuperClass(class_);
        resolveInterfaces(class_);
        this.classMap.put(class_.getName(), class_);
        return class_;
    }

    public Class_ parseClass(byte[] bytes) {
        ClassFile cf = ClassFile.Parse(bytes);
        return new Class_(cf);
    }

    public void resolveSuperClass(Class_ class_) {
        if (!class_.getName().equals("java/lang/Object")) {
            class_.setSuperClass(class_.getLoader().loadClass(class_.getSuperClassName()));
        }
    }

    public void resolveInterfaces(Class_ class_) {
        int interCount = class_.getInterfaceNames().length;
        if (interCount > 0) {
            class_.setInterfaces(new Class_[interCount]);
            for (int i = 0; i < interCount; i++) {
                class_.getInterfaces()[i] = class_.getLoader()
                        .loadClass(class_.getInterfaceNames()[i]);
            }
        }
    }

    public void link(Class_ class_) {
        // 验证
        vertify(class_);
        // 准备：在方法区中为变量分配内存，这里的变量指static修饰。
        prepare(class_);
    }

    public void vertify(Class_ class_) {
        // todo
    }

    public void prepare(Class_ class_) {
        // todo
        // 计算实例字段的个数
        calcInstanceFieldSlotIds(class_);
        // 计算静态字段的个数
        calcStaticFieldSlotIds(class_);
        // 给类变量分配空间，然后给他们赋予初始值
        allocAndInitStaticVars(class_);
    }

    public void calcInstanceFieldSlotIds(Class_ class_) {
        int slotId = 0;
        if (class_.getSuperClass() != null) {
            // 从上往下，先把父类的个数加上
            slotId = class_.getSuperClass().getInstanceSlotCount();
        }

        for (int i = 0; i < class_.getFields().length; i++) {
            if (!class_.getFields()[i].isStatic()) {
                class_.getFields()[i].slotId = slotId;
                slotId++;
                if (class_.getFields()[i].isLongOrDouble()) {
                    slotId++;
                }
            }
        }
       class_.setInstanceSlotCount(slotId);
    }

    public void calcStaticFieldSlotIds(Class_ class_) {
        int slotId = 0;
        for (int i = 0; i < class_.getFields().length; i++) {
            if (class_.getFields()[i].isStatic()) {
                class_.getFields()[i].slotId = slotId;
                slotId++;
                if (class_.getFields()[i].isLongOrDouble()) {
                    slotId++;
                }
            }
        }
        class_.setStaticSlotCount(slotId);
    }

    public void allocAndInitStaticVars(Class_ class_) {
        // LocalVars是一个Slot[]的包装类，这里用来定位和初始化
        class_.staticVars = new LocalVars_(class_.getStaticSlotCount());
        for (MyField myField : class_.getFields()) {
            if (myField.isStatic() && myField.isFinal()) {
                initStaticFinalVar(class_, myField);
            }
        }
    }

    public void initStaticFinalVar(Class_ class_, MyField myField) {
        LocalVars_ vars = class_.staticVars;
        RuntimeConstantPool cp = class_.getRuntimeConstantPool();
        // 在此强调一下，这里constValue_index的是static final
        int cpIndex = myField.constValue_index;
        int slotId = myField.slotId;

        // 《深入》P177，这个final static根据不同类型，获取相应类型的值
        if (cpIndex > 0) {
            switch (myField.getDescriptor()) {
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
