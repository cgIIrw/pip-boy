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

//    public Composite_Entry() {
//        compositeEntry = new ArrayList<>();
//    }

    public Composite_Entry(String path) {
        compositeEntry = new ArrayList<>();
        for(String p: path.split(Entry.pathListSeparator)) {
            compositeEntry.add(NewEntry.newEntry(p));
        }
    }
    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry: compositeEntry) {
            if (entry.readClass(className) != null) {
                return entry.readClass(className);   // 不考虑多过路径返回多个结果
            }
        }
//        Iterator<Entry> itr = compositeEntry.iterator();
//        while(itr.hasNext()) {
//            Entry it = itr.next();
//            if(it.readClass(className) != null) {
//                return it.readClass(className);
//            }
//        }
        return null;
    }

    @Override
    public String string() {
        StringBuilder sb = new StringBuilder();
        for (Entry entry: compositeEntry) {
            sb.append(entry.string());
            sb.append(Entry.pathListSeparator);
        }
//        Iterator<Entry> strs = compositeEntry.iterator();
//        while(strs.hasNext()) {
//            Entry it = strs.next();
//            sb.append(it.string());
//            sb.append(Entry.pathListSeparator);
//        }
        return sb.toString();
    }

//    public void addEntry(Entry entry) {
//        compositeEntry.add(entry);
//    }
}
