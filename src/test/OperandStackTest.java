package test;

import org.junit.Test;
import rtda.OperandStack;

import static org.junit.Assert.*;

public class OperandStackTest {
    OperandStack per = new OperandStack(8);


    @Test
    public void pushInt() {
        per.pushInt(100);
        per.pushFloat(-3.14159265f);
        per.pushLong(-2997924580L);
        per.pushRef(null);
        per.pushDouble(-2.71818181828456);
        System.out.println(per.popDouble());
        System.out.println(per.popRef());
        System.out.println(per.popLong());
        System.out.println(per.popFloat());
        System.out.println(per.popInt());
    }

    @Test
    public void pushFloat() {
        per.pushFloat(-3.14159265f);
        System.out.println(per.popFloat());

    }

    @Test
    public void pushLong() {
        per.pushLong(-2997924580L);
        System.out.println(per.popLong());
    }

    @Test
    public void pushDouble() {
        per.pushDouble(-2.71818181828456);
        System.out.println(per.popDouble());
    }

    @Test
    public void pushRef() {
        per.pushRef(null);
        System.out.println(per.popRef());
    }
}