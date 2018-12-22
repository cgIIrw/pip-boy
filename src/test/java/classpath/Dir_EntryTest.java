package classpath;

import org.junit.Test;

public class Dir_EntryTest {
    Dir_Entry dir_entry = new Dir_Entry(".");

    public Dir_EntryTest() {
    }

    @Test
    public void getString() {
        System.out.println(dir_entry.getString());
    }

    @Test
    public void readClass() {
        System.out.println(dir_entry.readClass("Hello.txt") != null);
    }
}