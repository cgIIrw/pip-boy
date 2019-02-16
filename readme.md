# <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzn3vikirtj314i0zo4qq.jpg" width = "50" height = "50"/> Pip-Boy

Pip-Boy是一款采用Java语言实现的简易版Java虚拟机。

## <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzohopiiwaj30qa0n2tgd.jpg" width = "45" height = "43"/> 开发环境 & 项目依赖

- IntelliJ IDEA 2018.3.3 (CE)
- Mac OS X 10.11.6
- JDK 1.8.0_171
- hamcrest-core 1.3
- junit 4.12
- commons-cli 1.4

## <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzmlu4yr5ij30iu0i0qb7.jpg" width = "45" height = "45"/> 功能

- [x] 编写执行.class文件的命令行工具
- [x] 读取.class文件字节码
- [x] 完成类加载
- [x] 实现多态、方法分派以及类初始化
- [x] 构建数组类型
- [x] 实现字符串部分功能
- [x] 实现native方法调用
- [x] 实现基本的反射功能
- [ ] GC (开发中)
- [ ] JIT (开发中)

## <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzohav632mj306o06oaax.jpg" width = "45" height = "44"/> 演示

### 查看版本

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/version.svg">
</p>

### -Xjre & -cp

-Xjre用来指定jre的位置，-cp/-classpath用来指定用户目录：

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/Xjre-cp.svg">
</p>

-Xjre也可以用读取字节码的方式进行说明：https://asciinema.org/a/xHSYmlOKVpWmTj8J4MS49aw29

### 多态

```java
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
```

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/Poly.svg">
</p>


### 数组

```java
public class FindNumberWithSum {
    public static int[] findNumberWithSum(int[] data, int sum) {
        int left = 0;
        int right = data.length - 1;
        int[] re = new int[2];

        while ((data[left] + data[right]) != sum && left < right) {
            if ((data[left] + data[right]) < sum) {
                left = left + 1;
            } else if ((data[left] + data[right]) > sum) {
                right = right - 1;
            }
        }
        if ((data[left] + data[right]) == sum) {
            re[0] = data[left];
            re[1] = data[right];
            return re;

        }
        return null;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 4, 7, 11, 15};
        int sum = 18;
        System.out.println(findNumberWithSum(array, sum)[0]);
        System.out.println(findNumberWithSum(array, sum)[1]);
    }
}
```

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/FindNumberWithSum.svg">
</p>

### 反射

```java
public class ClassTest {
    public static void main(String args[]) throws Exception {
        Class objectClass = ClassTest.class;
        ClassTest o = new ClassTest();
        Class objectClass02 = o.getClass();
        System.out.println(objectClass.getName());
        System.out.println(objectClass02.getName());
    }
}
```

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/reflect.svg">
</p>

### native方法调用

计算哈希值涉及到native方法的调用，本例打印hashCode()返回的结果，代码如下：

```java
public class HashCodeTest {
    public static void main(String[] args) {
        Object hashCodeTest = new HashCodeTest();
        System.out.println(hashCodeTest.hashCode());
    }
}
```

<p align="center">
  <img width="600" src="https://github.com/cgIIrw/pip-boy/blob/master/svg/native.svg">
</p>

## <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzohceh2s6j30d90a9dis.jpg" width = "47" height = "40"/> 文档

## <img src="http://ww1.sinaimg.cn/large/006QHM1zly1fzmmunxv61j30ht0ii777.jpg" width = "45" height = "45"/> 参考

**声明**： 本项目未有商业之用途，所引《辐射》系列之图片仅限于交流、欣赏与致敬，其版权由相关公司拥有。
