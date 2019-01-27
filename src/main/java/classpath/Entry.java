package classpath;

import java.io.*;

// 路径接口
public interface Entry {

    // 多个路径之间的分隔符，根据操作系统的不同而不同
    String pathListSeparator = File.pathSeparator;

    // 返回变量的字符串表示
    String getString();

    // class文件的包路径
    byte[] readClass(String className) throws IOException;
}

