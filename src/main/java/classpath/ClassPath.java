package classpath;

import java.io.*;

/**
 * Created by cgrw on 18/4/2.
 */
public class ClassPath {
    private Entry bootClasspath;
    private Entry exClasspath;
    private Entry userClasspath;

    public static ClassPath parse(String jreOption, String cpOption) {
        ClassPath cp = new ClassPath();

        // 解析启动路径和扩展路径
        cp.parseBootAndExtClasspath(jreOption);

        // 解析用户路径
        cp.parseUserClasspath(cpOption);
        return cp;
    }

    private void parseBootAndExtClasspath(String jreOption) {
        String jreDir = getJreDir(jreOption);

        // jre/lib/*
        String jreLibPath = jreDir + File.separator + "lib" + File.separator + "*";

        // 获得实例，可以通过相关方法，读取class
        bootClasspath = new Wildcard_Entry(jreLibPath);

        // jre/lib/ext/*
        String jreExtPath = jreDir + File.separator + "lib" + File.separator + "ext" +
                File.separator + "*";
        exClasspath = new Wildcard_Entry(jreExtPath);
    }

    private String getJreDir(String jreOption) {

        // Java环境变量的地址
        String jh = System.getenv("JAVA_HOME");
//        String jh = "/Library/Java/JavaVirtualMachines/jdk1.8.0_171.jdk/Contents/Home";
        if (jreOption != null && !jreOption.equals("")) {
            return jreOption;
        }

        // 如果没有输入，那么就在当前目录下查找jre
        File file = new File("jre");

        // 如果此时jre确实存在，则将其绝对路径返回
        if (file.exists()) {
            return file.getAbsolutePath();
        }

        // 如果jre不存在，那么在环境变量去寻找
        if (jh != null && !jh.equals("")) {
            return jh + File.separator + "jre";
        } else {
            // 还找不到只能抛运行时异常
            throw new RuntimeException("找不到jre文件！");
        }
    }

    private void parseUserClasspath(String cpOption) {

        // 如果传入的字符串为null或者空字符串，则默认为当前目录下
        if (cpOption == null || cpOption.equals("")) {
            cpOption = ".";
        }
        userClasspath = NewEntry.newEntry(cpOption);
    }

    public byte[] readClass(String className) throws IOException {
        className = className + ".class";

        // 读取的顺序是系统路径，扩展路径以及用户路径
        if (bootClasspath.readClass(className) != null) {
            return bootClasspath.readClass(className);
        }
        if (exClasspath.readClass(className) != null) {
            return exClasspath.readClass(className);
        }
        if (userClasspath.readClass(className) != null) {
            return userClasspath.readClass(className);
        } else {
            throw new RuntimeException("不能读取到.class文件！");
        }
    }
}
