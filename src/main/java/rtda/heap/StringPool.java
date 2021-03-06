package rtda.heap;

import rtda.methodarea.ClassLoader_;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Field_;

import java.util.HashMap;

public class StringPool {

    private static HashMap<String, Instance_> internedStrings = new HashMap<>();

    // Java的String是以char数组来表现的，这里的str可以理解为运行时常量池的
    // 字符串常量，这里需要解析来完成Java String应该具备的基本功能，而因为编
    // 写的代码使用的是Java，所以是用Java语言来实现Java String
    public static Instance_ jString(ClassLoader_ loder, String str) {
        if (internedStrings.containsKey(str))
            return internedStrings.get(str);

        // String内部维护一个value字段，它是一个char数组
        char[] chars = str.toCharArray();
        Instance_ newchars = new Instance_(loder.loadClass("[C"), chars.length);
        for (int i = 0; i < chars.length; i++)
            newchars.getFields().getSlots()[i].setNum(chars[i]);

        // 加载String类，String虽然不同于基本类型，但不要忘了它和数组的
        // 加载机制不一样
        InstanceKlass_ stringClass = loder.loadClass("java/lang/String");
        // 给字符串在JVM中创建实例
        Instance_ stringInstance = stringClass.newObject();
        // 在stringClass中寻找满足条件的字段，计算其slotId，然后在
        // 字符串实例中，给对应id的字段传递值
        for (Field_ f : stringInstance.getInstanceKlass_().getFields()) {
            if (!f.isStatic() && f.getName().equals("value")
                    && f.getDescriptor().equals("[C")) {
                stringInstance.getFields().getSlots()[f.getSlotId()].setRef(newchars);
            }
        }

        // 将str和JVM内部的实例进行缓存
        internedStrings.put(str, stringInstance);
        return stringInstance;
    }

    public static String goString(Instance_ jStr) {

        // 这个jStr相当于jString中的stringInstance
        // 首先要获得value引用的字符数组，可以通过for
        // 循环查找，也可以通过slotId来获取
        Instance_ newChars = null;
        for (Field_ f : jStr.getInstanceKlass_().getFields()) {
            if (!f.isStatic() && f.getName().equals("value")
                    && f.getDescriptor().equals("[C")) {
                newChars = jStr.getFields().getSlots()[f.getSlotId()].getRef();
            }
        }
        if (newChars != null) {
            int len = newChars.getFields().getSlots().length;
            char[] chars = new char[len];

            for (int i = 0; i < len; i++)
                chars[i] = (char) (newChars.getFields().getSlots()[i].getNum());

            return new String(chars);
        }
        return null;
    }
}
