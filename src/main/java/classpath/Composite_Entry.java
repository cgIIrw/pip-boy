package classpath;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yin on 18/4/2.
 */
public class Composite_Entry implements Entry {
    private List<Entry> compositeEntry;

    Composite_Entry(String path) {
        compositeEntry = new ArrayList<>();
        for (String p : path.split(Entry.pathListSeparator)) {
            compositeEntry.add(NewEntry.newEntry(p));
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : compositeEntry) {
            if (entry.readClass(className) != null) {

                // 不考虑多个路径返回多个结果
                return entry.readClass(className);
            }
        }
        return null;
    }

    @Override
    // 将所有路径的绝对路径拼接在一起返回
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : compositeEntry) {
            sb.append(entry.getString());
            sb.append(Entry.pathListSeparator);
        }
        return sb.toString();
    }
}
