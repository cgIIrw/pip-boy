package classpath;

import java.io.*;

/**
 * Created by yin on 18/4/2.
 */
class Dir_Entry implements Entry {
    private String absDir;

    Dir_Entry(String path) {
        File file = new File(path);
        if (file.exists()) {
            try {
                absDir = file.getCanonicalPath();
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
        byte[] buf = new byte[1024];
        byte[] b = null;
        File f = new File(absDir, className);

        // 如果文件不存在，直接返回null
        if (!f.exists()) {
            return null;
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(f));
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int bufread = 0;
            while ((bufread = in.read(buf)) != -1) {
                out.write(buf, 0, bufread);
            }
            b = out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
