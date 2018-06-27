package test;

import org.junit.Test;
import rtda.LocalVars;

import static org.junit.Assert.*;

public class LocalVarsTest {
    LocalVars testlv = new LocalVars(10);

    @Test
    public void setInt() {

        testlv.setInt(1, -100);
        System.out.println(testlv.getInt(1));
    }

    @Test
    public void setFloat() {
        testlv.setFloat(2, 3.145926f);
        System.out.println(testlv.getFloat(2));
    }

    @Test
    public void setLong() {
        testlv.setLong(3, -2997922223314580L);
        System.out.println(testlv.getLong(3));
    }

    @Test
    public void setDouble() {
        testlv.setDouble(4, -2.71828182845);
        System.out.println(testlv.getDouble(4));
    }

    @Test
    public void setRef() {
        testlv.setRef(5, null);
        System.out.println(testlv.getRef(5));
    }
}