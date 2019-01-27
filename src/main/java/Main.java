import classpath.ClassPath;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;
import rtda.methodarea.ClassLoader_;
import rtda.methodarea.Class_;
import rtda.methodarea.Method_;

import java.io.File;
import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws ParseException {
        String className = getMainName();

        CommandLine cmd = Cmd.cmd(args);

        if (cmd.hasOption("version")) {
//            System.out.println("version 0.0.1");
            FileInputStream fis;
            try {
                File file = new File("src/main/resources/banner.text");
                fis = new FileInputStream(file);
                byte[] b = new byte[(int) file.length()];
                while (fis.read(b) != -1)
                    System.out.println(new String(b));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (cmd.hasOption("help") || cmd.hasOption("?") || args.length == 0) {
            Cmd.printUsage(className);
        } else {
            startJVM(cmd, args);
        }
    }

    private static void startJVM(CommandLine cmd, String[] args) {
        String jreOption = "";
        String cpOption = "";
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
        Class_ mainClass = classLoader.loadClass(className);
        for (Method_ method : mainClass.getMethods()) {
            if (method.getName().equals("main")) {
                Interpret.interpret(method, false);
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

    // 该方法用来返回静态方法main所属的类的类名
    private static String getMainName() {
        return new Object() {
            String getClassName() {
                String clazzName = this.getClass().getName();
                return clazzName.substring(0, clazzName.lastIndexOf('$'));
            }
        }.getClassName();
    }
}
