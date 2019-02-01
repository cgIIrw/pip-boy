public class Poly {
    public static void main(String[] args) {
      A instance = new B();
      instance.printMethod();
    }

    static class A {
      public void printMethod() {
        System.out.println("I am A");
      }
    }

    static class B extends A {
      @Override
      public void printMethod() {
        System.out.println("I am B");
      }
    }
}
