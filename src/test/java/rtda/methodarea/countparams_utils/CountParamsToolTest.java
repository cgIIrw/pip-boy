package rtda.methodarea.countparams_utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountParamsToolTest {
    String descriptor = "(DILjava/lang/String;LClassFileDemo;)Ljava/lang/String;";

    @Test
    public void countMethod() {
        int a = CountParamsTool.countMethod(descriptor);
        System.out.println(a);
    }
}