package rtda.methodarea.rtcp.symref;

import rtda.methodarea.Class_;
import rtda.methodarea.Method_;

//
public class MethodLookup {

    public static Method_ lookupMethodInClass(Class_ class_, String name, String descriptor) {

        // 在class_和它的父类中递归查找简单名和描述符都与目标相匹配的方法
        for (Class_ c = class_; c != null; c = c.getSuperClass()) {
            for (Method_ method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static Method_ lookupMethodInInterfaces(Class_[] ifaces, String name, String descriptor) {

        // 传入的ifaces是接口列表，在它们以及它们的父接口递归查找简单名和描述符都与目标相匹配的方法
        for (Class_ iface : ifaces) {
            for (Method_ method : iface.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }

            // 这是一个深度优先的搜索
            Method_ method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
