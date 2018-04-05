package classpath;

import java.io.*;

/**
 * Created by yin on 18/4/2.
 */
class Dir_Entry implements Entry {
    private String absDir;

    public Dir_Entry(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                absDir = file.getCanonicalPath();
            } else {
                absDir = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public String string() {
        return absDir;
    }

    @Override
    public byte[] readClass(String className) {
        byte[] buf = new byte[1024];
        byte[] b = null;
        File f;
        if (absDir != null) {
            f = new File(absDir, className);
            if (!f.exists()) {
                return null;
            }
        } else {
            f = new File(className);
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
