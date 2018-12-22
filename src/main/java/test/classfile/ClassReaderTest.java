package test.classfile; 

import classfile.ClassReader;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* ClassReader Tester. 
* 
* @author <Authors name> 
* @since <pre>四月 14, 2018</pre> 
* @version 1.0 
*/ 
public class ClassReaderTest {

    byte[] data = "abcdef".getBytes();

    ClassReader classReader = new ClassReader(data);


@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: readUint8() 
* 
*/ 
@Test
public void testReadUint8() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: readUint16() 
* 
*/ 
@Test
public void testReadUint16() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: byteToInt(byte[] bytes) 
* 
*/ 
@Test
public void testByteToInt() throws Exception {
//TODO: Test goes here...
    byte[] test = "ab".getBytes();
    Assert.assertEquals(classReader.readBytes(2), test);
} 


} 
