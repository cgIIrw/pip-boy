package rtda.heap;

public class MethodLookup {

    public static MyMethod lookupMethodInClass(Class_ class_, String name, String descriptor) {

        // 在myclass和它的父类中递归查找简单名和描述符都与目标相匹配的方法
        for (Class_ c = class_; c != null; c = c.getSuperClass()) {
            for (MyMethod method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static MyMethod lookupMethodInInterfaces(Class_[] ifaces, String name, String descriptor) {

        // 传入的ifaces是接口列表，在它们以及它们的父接口递归查找简单名和描述符都与目标相匹配的方法
        for (Class_ iface : ifaces) {
            for (MyMethod method : iface.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }

            MyMethod method = lookupMethodInInterfaces(iface.getInterfaces(), name, descriptor);
            if (method != null) {
                return method;
            }
        }
        return null;
    }
}
