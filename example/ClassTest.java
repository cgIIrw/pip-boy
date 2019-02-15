public class ClassTest {
    public static void main(String args[]) throws Exception {
        Class objectClass = ClassTest.class;
        ClassTest o = new ClassTest();
        Class objectClass02 = o.getClass();
        System.out.println(objectClass.getName());
        System.out.println(objectClass02.getName());
    }
}
