package classpath;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by yin on 18/4/2.
 */
class Zip_Entry implements Entry {
    private String absPath;

    public Zip_Entry(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                absPath = file.getCanonicalPath(); // 带.jar等后缀的绝对路径
            } else {
                absPath = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File file = new File(absPath);
        ZipFile zipFile = new ZipFile(file); // 这一步要抛出异常
        ZipEntry entry = zipFile.getEntry(className);
        if (entry == null) {
            return null;
        }
        byte[] buf = new byte[1024];
        byte[] b;

        try (InputStream input = zipFile.getInputStream(entry);
             BufferedInputStream in = new BufferedInputStream(input);
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int bufread;
            while ((bufread = in.read(buf)) != -1) {
                out.write(buf, 0, bufread);
            }
            b = out.toByteArray();
        }
        return b;
    }

    @Override
    public String string() {
        return absPath;
    }
}