package rtda.methodarea;

import classfile.ClassFile;
import classpath.ClassPath;
import rtda.heap.Instance_;
import rtda.methodarea.countfields_utils.StaticFieldsCounter;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.LocalVars_;

import java.io.IOException;
import java.util.HashMap;

// 类加载器
public class ClassLoader_ {
    private ClassPath cp;
    private HashMap<String, Class_> classMap;

    public ClassLoader_(ClassPath cp) {
        this.cp = cp;
        classMap = new HashMap<>();

    }

    // name是完全限定名
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
        // 这里就已经完成了字节码到Class_对象的转换，基本思路就是一个"空"架子，往
        // 里面填东西，并没有什么黑科技
        Class_ class_ = parseClass(bytes);
        // 相当于标记一下是谁加载的这个类(当前的类加载器加载的类)
        class_.setLoader(this);
        // 和对象不一样，子类的Class是需要加载父类的Class的
        resolveSuperClass(class_);
        resolveInterfaces(class_);
        this.classMap.put(class_.getThisClassName(), class_);
        return class_;
    }

    public Class_ parseClass(byte[] bytes) {
        ClassFile cf = ClassFile.Parse(bytes);
        return new Class_(cf);
    }

    public void resolveSuperClass(Class_ class_) {
        if (!class_.getThisClassName().equals("java/lang/Object")) {
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
//        calcInstanceFieldSlotIds(class_);
        // 计算静态字段的个数
        StaticFieldsCounter.countsStaticFields(class_);
        // 给类变量分配空间，然后给他们赋予初始值
        allocAndInitStaticVars(class_);
    }

//    public void calcInstanceFieldSlotIds(Class_ class_) {
//        int slotId = 0;
//        if (class_.getSuperClass() != null) {
//            // 从上往下，先把父类的个数加上
//            slotId = class_.getSuperClass().getInstanceSlotCount();
//        }
//
//        for (int i = 0; i < class_.getFields().length; i++) {
//            if (!class_.getFields()[i].isStatic()) {
//                class_.getFields()[i].setSlotId(slotId);
//                slotId++;
//                if (class_.getFields()[i].isLongOrDouble()) {
//                    slotId++;
//                }
//            }
//        }
//        class_.setInstanceSlotCount(slotId);
//    }

//    public void calcStaticFieldSlotIds(Class_ class_) {
//        int slotId = 0;
//        for (int i = 0; i < class_.getFields().length; i++) {
//            if (class_.getFields()[i].isStatic()) {
//                class_.getFields()[i].setSlotId(slotId);
//                slotId++;
//                if (class_.getFields()[i].isLongOrDouble()) {
//                    slotId++;
//                }
//            }
//        }
//        class_.setStaticSlotCount(slotId);
//    }

    private void allocAndInitStaticVars(Class_ class_) {
        // LocalVars是一个Slot[]的包装类，这里用来定位和初始化
        class_.setStaticVars(new LocalVars_(class_.getStaticSlotCount()));
        for (Field_ field : class_.getFields()) {
//            if (field.isStatic() && field.isFinal()) {
            initStaticFinalVar(class_, field);
//            }
        }
    }

    private void initStaticFinalVar(Class_ class_, Field_ field) {
        LocalVars_ vars = class_.getStaticVars();
        RuntimeConstantPool_ cp = class_.getRuntimeConstantPool();
        // 再次强调一下，这里constValue_index的是static final
//        int cpIndex = field.getConstValue_index();
        int slotId = field.getSlotId();
        int cpIndex;

        // 《深入》P177，这个final static根据不同类型，获取相应类型的值
        // 如果是static且final的常量，则赋常量值
        if (field.isStatic() && field.isFinal() &&
                (cpIndex = field.getConstValue_index()) > 0) {
            switch (field.getDescriptor()) {
                // 非long double float类型转化为int存储
                case "Z": // 基本类型boolean
                case "B": // 基本类型byte
                case "C": // 基本类型char
                case "S": // 基本类型short
                case "I": // 基本类型int
                    int val = (int) (cp.getConstant(cpIndex).getVal());
                    vars.setInt(slotId, val);
                    break;
                case "J":
                    long lval = (long) (cp.getConstant(cpIndex).getVal());
                    vars.setLong(slotId, lval);
                    break;
                case "F":
                    float fval = (float) (cp.getConstant(cpIndex).getVal());
                    vars.setFloat(slotId, fval);
                    break;
                case "D":
                    double dval = (double) (cp.getConstant(cpIndex).getVal());
                    vars.setDouble(slotId, dval);
                    break;
                case "Ljava/lang/String;":
                    // todo
                    break;
                default:
                    break;
            }
        } else {
            // static final只限于基本类型加String类型
            switch (field.getDescriptor()) {
                // 非long double float类型转化为int存储
                case "Z": // 基本类型boolean
                case "B": // 基本类型byte
                case "C": // 基本类型char
                case "S": // 基本类型short
                case "I": // 基本类型int
                    int val = (0);
                    vars.setInt(slotId, val);
                    break;
                case "J":
                    long lval = (0L);
                    vars.setLong(slotId, lval);
                    break;
                case "F":
                    float fval = (0.0f);
                    vars.setFloat(slotId, fval);
                    break;
                case "D":
                    double dval = (0.0);
                    vars.setDouble(slotId, dval);
                    break;
                case "Ljava/lang/String;":
                    // todo
                    break;
                default:
                    Instance_ refval = null;
                    vars.setRef(slotId, refval);
                    break;
            }
        }
    }
}
