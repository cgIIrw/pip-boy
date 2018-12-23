package classpath;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yin on 18/4/2.
 */
public class Wildcard_Entry implements Entry {
    private List<Entry> cp = new ArrayList<>();

    Wildcard_Entry(String path) {

        String baseDir = path.substring(0, path.length() - 1);
        File file = new File(baseDir);
        if (!file.exists())
            throw new RuntimeException("不存在当前路径！");
        File[] files = file.listFiles();
        if (files == null) {
            throw new RuntimeException("没有获得目录下的子文件和子目录！");
        }
        for (File f : files) {
            // 如果当前遍历的目录下的实体不是文件，则跳过单次遍历
            if (!f.isFile())
                continue;

            if (f.getName().endsWith(".jar") || f.getName().endsWith(".JAR")) {
                cp.add(new Zip_Entry(baseDir + f.getName()));
            }
        }
        // 当前目录下有可能存在目标文件，且同一目录下不会有同名的多个文件
        // 所以添加一个Dir_Entry对象处理
        cp.add(new Dir_Entry(baseDir));
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        for (Entry entry : cp) {
            if (entry.readClass(className) != null) {
                // 不考虑多个路径返回多个结果
                return entry.readClass(className);
            }
        }
        return null;
    }

    @Override
    public String getString() {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : cp) {
            sb.append(entry.getString());
            sb.append(Entry.pathListSeparator);
        }
        return sb.toString();
    }
}
