package rtda.methodarea;

import classfile.ClassFile;
import classpath.ClassPath;
import rtda.heap.Instance_;
import rtda.heap.StringPool;
import rtda.methodarea.countfields_utils.StaticFieldsCounter;
import rtda.methodarea.rtcp.RuntimeConstantPool_;
import rtda.stack.LocalVars_;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;

// 类加载器
public class ClassLoader_ {
    private ClassPath cp;
    private HashMap<String, InstanceKlass_> classMap;

    public ClassLoader_(ClassPath cp) {
        this.cp = cp;
        classMap = new HashMap<>();
    }

    // name是完全限定名
    public InstanceKlass_ loadClass(String name) {
        if (classMap.containsKey(name)) {
            return classMap.get(name);
        }

        if (name.startsWith("["))
            return loadArrayClass(name);
        // 数组类的数据不来自Class文件，运行时生成，要单独考虑
        return loadNonArrayClass(name);
    }

    private InstanceKlass_ loadArrayClass(String name) {
        InstanceKlass_ instanceKlass_ = new InstanceKlass_(name, this);
        this.classMap.put(name, instanceKlass_);
        return instanceKlass_;
    }

    private InstanceKlass_ loadNonArrayClass(String name) {
        byte[] data = readClass(name);
        InstanceKlass_ instanceKlass_ = defineClass(data);
        link(instanceKlass_);
        return instanceKlass_;
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

    public InstanceKlass_ defineClass(byte[] bytes) {
        // 这里就已经完成了字节码到Class_对象的转换，基本思路就是一个"空"架子，往
        // 里面填东西，并没有什么黑科技
        InstanceKlass_ instanceKlass_ = parseClass(bytes);
        // 相当于标记一下是谁加载的这个类(当前的类加载器加载的类)
        instanceKlass_.setLoader(this);
        // 和对象不一样，子类的Class是需要加载父类的Class的
        resolveSuperClass(instanceKlass_);
        resolveInterfaces(instanceKlass_);
        this.classMap.put(instanceKlass_.getThisClassName(), instanceKlass_);
        return instanceKlass_;
    }

    public InstanceKlass_ parseClass(byte[] bytes) {
        ClassFile cf = ClassFile.Parse(bytes);
        return new InstanceKlass_(cf);
    }

    public void resolveSuperClass(InstanceKlass_ instanceKlass_) {
        if (!instanceKlass_.getThisClassName().equals("java/lang/Object")) {
            instanceKlass_.setSuperClass(instanceKlass_.getLoader().loadClass(instanceKlass_.getSuperClassName()));
        }
    }

    public void resolveInterfaces(InstanceKlass_ instanceKlass_) {
        int interCount = instanceKlass_.getInterfaceNames().length;
        if (interCount > 0) {
            instanceKlass_.setInterfaces(new InstanceKlass_[interCount]);
            for (int i = 0; i < interCount; i++) {
                instanceKlass_.getInterfaces()[i] = instanceKlass_.getLoader()
                        .loadClass(instanceKlass_.getInterfaceNames()[i]);
            }
        }
    }

    public void link(InstanceKlass_ instanceKlass_) {
        // 验证
        vertify(instanceKlass_);
        // 准备：在方法区中为变量分配内存，这里的变量指static修饰。
        prepare(instanceKlass_);
    }

    public void vertify(InstanceKlass_ instanceKlass_) {
        // todo
    }

    public void prepare(InstanceKlass_ instanceKlass_) {
        // todo
        // 计算实例字段的个数
//        calcInstanceFieldSlotIds(instanceKlass_);
        // 计算静态字段的个数
        StaticFieldsCounter.countsStaticFields(instanceKlass_);
        // 给类变量分配空间，然后给他们赋予初始值
        allocAndInitStaticVars(instanceKlass_);
    }

//    public void calcInstanceFieldSlotIds(InstanceKlass_ class_) {
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

//    public void calcStaticFieldSlotIds(InstanceKlass_ class_) {
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

    private void allocAndInitStaticVars(InstanceKlass_ instanceKlass_) {
        // LocalVars是一个Slot[]的包装类，这里用来定位和初始化
        instanceKlass_.setStaticVars(new LocalVars_(instanceKlass_.getStaticSlotCount()));
        for (Field_ field : instanceKlass_.getFields()) {
//            if (field.isStatic() && field.isFinal()) {
            initStaticFinalVar(instanceKlass_, field);
//            }
        }
    }

    private void initStaticFinalVar(InstanceKlass_ instanceKlass_, Field_ field) {
        LocalVars_ vars = instanceKlass_.getStaticVars();
        RuntimeConstantPool_ cp = instanceKlass_.getRuntimeConstantPool();
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
                    long lval = ((BigInteger) (cp.getConstant(cpIndex).getVal())).longValue();
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
                    String str = (String) (cp.getConstant(cpIndex).getVal());
                    Instance_ jStr = StringPool.jString(instanceKlass_.getLoader(), str);
                    vars.setRef(slotId, jStr);
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
                    Instance_ stringval = null;
                    vars.setRef(slotId, stringval);
                    break;
                default:
                    Instance_ refval = null;
                    vars.setRef(slotId, refval);
                    break;
            }
        }
    }
}
