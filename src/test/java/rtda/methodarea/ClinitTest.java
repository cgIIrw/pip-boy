package rtda.methodarea;

import org.junit.Test;

import static org.junit.Assert.*;

public class ClinitTest {
    private static boolean flag = false;

    @Test
    public void getClinitMethod() {

        while (!flag) {
            testMethod();
        }
        System.out.println("输出打印字符！");

    }

    private static void testMethod() {
        flag = true;

    }
}