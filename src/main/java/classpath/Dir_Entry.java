package classpath;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by cgrw on 18/4/2.
 */
class Dir_Entry implements Entry {
    private String absDir;
    private Path realPath;

    Dir_Entry(String path) {
//        File file = new File(path);
        Path path01 = Paths.get(path);
//        if (file.exists()) {
        if (Files.exists(path01)) {
            try {
//                absDir = file.getCanonicalPath();
                absDir = path01.toRealPath().toString();
                realPath = path01.toRealPath();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            absDir = null;
        }
    }

    @Override
    public String getString() {
        return absDir;
    }

    @Override
    public byte[] readClass(String className) {
//        byte[] buf = new byte[1024];
        byte[] b = null;
//        File f = new File(absDir, className);
        Path p = realPath.resolve(className);

        // 如果文件不存在，直接返回null
//        if (!f.exists()) {
        if (!Files.exists(p)) {
            return null;
        }

//        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
//             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
//            int bufread = 0;
//            while ((bufread = in.read(buf)) != -1) {
//                out.write(buf, 0, bufread);
//            }
//            b = out.toByteArray();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            b = Files.readAllBytes(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}
