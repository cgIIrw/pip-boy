package classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yin on 18/4/2.
 */
public class Wildcard_Entry implements Entry {
    List<Entry> cp = new ArrayList<>();


    public Wildcard_Entry(String path) {

        String baseDir = path.substring(0, path.length() - 1);
        File file = new File(baseDir);
        File[] files = file.listFiles();
        if (files == null) {
            throw new RuntimeException("没有获得目录下的子文件和子目录!");
        }
        for (File f : files) {
            if ((f.isFile() && f.getName().endsWith(".jar")) || (f.isFile() && f.getName().endsWith(".JAR"))) {
                Entry jarEntry = new Zip_Entry(baseDir + f.getName());
                cp.add(jarEntry);
            }
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : cp) {
            if (entry.readClass(className) != null) {
                return entry.readClass(className);   // 不考虑多过路径返回多个结果
            }
        }
        return null;
    }

    @Override
    public String string() {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : cp) {
            sb.append(entry.string());
            sb.append(Entry.pathListSeparator);
        }
        return sb.toString();

    }
}
