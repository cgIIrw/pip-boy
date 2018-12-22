package classpath;

import java.io.*;

/**
 * Created by yin on 18/4/2.
 */
public class ClassPath {
    Entry bootClasspath;
    Entry exClasspath;
    Entry userClasspath;

    public static ClassPath parse(String jreOption, String cpOption) {
        ClassPath cp = new ClassPath();
        cp.parseBootAndExtClasspath(jreOption);
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    public void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);
        // jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";
        // 获得了Composite_Entry实例后,就可以点用相关方法,读取class
        bootClasspath = new Wildcard_Entry(jreLibPath);
        // jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" +
                File.separator + "*";
        exClasspath = new Wildcard_Entry(jreExtPath);
    }

    public String getJreDir(String jreOption) {
        String jh = System.getenv("JAVA_HOME");
        if (!jreOption.equals("") && jreOption != null) {
            return jreOption;
        }
        File file = new File("jre");
        if (file.exists()) {
            return file.getAbsolutePath();
        }
        if (jh != "") {
            return jh + File.separator + "jre";
        } else {
            throw new RuntimeException("找不到jre文件!");
        }
    }

    public void parseUserClasspath(String cpOption) {
        if (cpOption.equals("") || cpOption != null) {
            cpOption = ".";
        }
        userClasspath = NewEntry.newEntry(cpOption);
    }


    public byte[] readClass(String className) throws IOException {
        className = className + ".class";

        if (bootClasspath.readClass(className) != null) {
            return bootClasspath.readClass(className);
        }
        if (exClasspath.readClass(className) != null) {
            return exClasspath.readClass(className);
        }
        if (userClasspath.readClass(className) != null) {
            return userClasspath.readClass(className);
        } else {
            throw new RuntimeException("Can not read class!");
        }
    }


    public String string() {
        return userClasspath.string();
    }
}
