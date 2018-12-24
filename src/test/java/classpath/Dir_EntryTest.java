package classpath;

import org.junit.Test;

import java.io.File;

public class Dir_EntryTest {
    Dir_Entry dir_entry = new Dir_Entry(".");

    public Dir_EntryTest() {
    }

    @Test
    public void getString() {
        System.out.println(dir_entry.getString() + " " + File.separator.charAt(0));
        String jh = System.getenv("JAVA_HOME");
        System.out.println(jh);
    }

    @Test
    public void readClass() {
        System.out.println(dir_entry.readClass("Hello.txt") != null);
    }
}