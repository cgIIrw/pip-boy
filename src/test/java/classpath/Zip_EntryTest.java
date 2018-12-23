package classpath;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Zip_EntryTest {

    Zip_Entry zip_entry = new Zip_Entry("/Users/yin/IdeaProjects/MyJVM/ystest.zip");

    @Test
    public void readClass() throws IOException {
        System.out.println(zip_entry.readClass("ystest.txt") != null);
    }

    @Test
    public void getString() {
        System.out.println(zip_entry.getString());
    }
}