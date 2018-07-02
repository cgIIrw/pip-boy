package rtda.heap;

import classfile.ClassFile;
import classpath.ClassPath;

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
        myclass.loader = this;
        // 和对象不一样，子类的Class是需要加载父类的Class的
        resolveSuperClass(myclass);
        resolveInterfaces(myclass);
        this.classMap.put(myclass.name, myclass);
        return myclass;
    }

    public Myclass parseClass(byte[] bytes) {
        ClassFile cf = ClassFile.Parse(bytes);
        return new Myclass(cf);
    }

    public void resolveSuperClass(Myclass myclass) {
        if (myclass.name != "java/lang/Object") {
            myclass.superClass = myclass.loader.loadClass(myclass.superClassName);
        }
    }

    public void resolveInterfaces(Myclass myclass) {
        int interCount = myclass.interfaceNames.length;
        if (interCount > 0) {
            myclass.interfaces = new Myclass[interCount];
            for (int i = 0; i < interCount; i++) {
                myclass.interfaces[i] = myclass.loader
                        .loadClass(myclass.interfaceNames[i]);
            }
        }
    }

    public void link(Myclass myclass) {
        // 验证
        vertify(myclass);
        // 准备：在方法区中为变量分配内存，这里的变量指的是static的。
        prepare(myclass);
    }

    public void vertify(Myclass myclass) {
        // todo
    }

    public void prepare(Myclass myclass) {
        // todo
    }
}
