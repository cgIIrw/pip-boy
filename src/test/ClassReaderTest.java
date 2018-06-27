package test;

import classfile.ClassReader;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassReaderTest {
    byte[] data = "abcdef".getBytes();

    ClassReader classReader = new ClassReader(data);


    @Test
    public void readBytes() {
        byte[] test = "ab".getBytes();
//        Assert.assertEquals(classReader.readBytes(2)[1], test[1]);
        Assert.assertEquals(classReader.readBytes(2)[0], test[0]);
    }
}