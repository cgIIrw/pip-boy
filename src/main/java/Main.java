import classpath.ClassPath;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import rtda.methodarea.ClassLoader_;
import rtda.methodarea.InstanceKlass_;
import rtda.methodarea.Method_;

import java.io.*;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws ParseException {
//        String className = getMainName();

        CommandLine cmd = Cmd.cmd(args);

        if (cmd.hasOption("version")) {
//            System.out.println("version 0.0.1");
            FileInputStream fis;
            try {
//                File file = new File("src/main/resources/banner.text");
                File file = null;
                String resource = "banner.text";
                URL res = Main_PrintByteCode.class.getClassLoader().getResource(resource);
                assert res != null;
                // 使得运行jar包可以读取resource资源
                if (res.toString().startsWith("jar:")) {
                    try {
                        InputStream input = Main_PrintByteCode.class.getResourceAsStream(resource);
                        file = File.createTempFile("tempfile", ".temp");
                        OutputStream out = new FileOutputStream(file);
                        int read;
                        byte[] bytes = new byte[1024];

                        while ((read = input.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        file.deleteOnExit();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    file = new File(res.getFile());
                }
                assert file != null;
                fis = new FileInputStream(file);
                byte[] b = new byte[(int) file.length()];
                while (fis.read(b) != -1)
                    System.out.println(new String(b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cmd.hasOption("help") || cmd.hasOption("?") || args.length == 0) {
            Cmd.printUsage();
        } else {
            startJVM(cmd, args);
        }
    }

    private static void startJVM(CommandLine cmd, String[] args) {
        String jreOption = "";
        String cpOption = "";
        boolean verboseFlag = false;
        int count = 0;

        if (cmd.hasOption("cp")) {
            // 因为默认Option接一个参数，所以暂时不用处理cp接一个
            // 以上参数的情形
            cpOption = cmd.getOptionValue("cp");
            count = count + 2;
        }
        if (cmd.hasOption("Xjre")) {
            jreOption = cmd.getOptionValue("Xjre");
            count = count + 2;
        }
        if (cmd.hasOption("v")) {
            String s = cmd.getOptionValue("v");
            if (s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false")) {
                verboseFlag = Boolean.valueOf(s);
                count = count + 2;
            } else {
                System.out.println("错误输入-verbose参数！");
                return;
            }
        }

//        // 当参数只有两个时，一个为Option，一个为类名称，相当于没有
//        // 为Option传参数，所以将其置为""
//        if (args.length == 2) {
//            jreOption = "";
//            cpOption = "";
//        }
        ClassPath cp = ClassPath.parse(jreOption, cpOption);

        ClassLoader_ classLoader = new ClassLoader_(cp);

        // File.separator.charAt(0)是为了取字符而非字符串
        String className = args[count].replace('.', File.separator.charAt(0));
        InstanceKlass_ mainClass = classLoader.loadClass(className);
        for (Method_ method : mainClass.getMethods()) {
            if (method.getName().equals("main")) {
                Interpret.interpret(method, verboseFlag);
            }
        }

//        byte[] classData = null;
//        try {
//            classData = cp.readClass(className);
//        } catch (IOException e) {
//            System.out.printf("不能找到和加载主类 %s\n", args[count]);
//        }
//        for (int i = 0; i < (classData != null ? classData.length : 0); i++) {
//            if (i % 20 == 0) {
//                System.out.println();
//            }
//            System.out.printf("%02X ", classData[i]); // 此打印如《深入理解Java虚拟机》显示
//        }
    }

//    // 该方法用来返回静态方法main所属的类的类名，使用内部类对象反射的方式
//    private static String getMainName() {
//        return new Object() {
//            String getClassName() {
//                String clazzName = this.getClass().getName();
//                return clazzName.substring(0, clazzName.lastIndexOf('$'));
//            }
//        }.getClassName();
//    }
}
