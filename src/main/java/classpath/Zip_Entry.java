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
        // 创建file对象，如果文件系统也存在相应的文件，
        // 那么在后续中它实际代表一个路径为path的压缩文件(jar等)
        File file = new File(path);
        try {
            if (file.exists()) {
                // 文件存在的时候返回绝对路径(包含压缩后缀)
                absPath = file.getCanonicalPath();
            } else {
                absPath = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    // className带文件类型后缀
    public byte[] readClass(String className) throws IOException {
        File file = new File(absPath);

        // 需要抛出一个IO异常
        ZipFile zipFile = new ZipFile(file);
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
    public String getString() {
        return absPath;
    }
}