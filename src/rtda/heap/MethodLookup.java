package rtda.heap;

public class MethodLookup {

    public static MyMethod lookupMethodInClass(Myclass myclass, String name, String descriptor) {
        for (Myclass c = myclass; c != null; c = c.getSuperClass()) {
            for (MyMethod method : c.getMethods()) {
                if (method.getName().equals(name) && method.getDescriptor().equals(descriptor)) {
                    return method;
                }
            }
        }
        return null;
    }

    public static MyMethod lookupMethodInInterfaces(Myclass[] ifaces, String name, String descriptor) {
        for (Myclass iface : ifaces) {
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
