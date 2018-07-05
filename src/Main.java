import classfile.ClassFile;
import classfile.MemberInfo;
import org.apache.commons.cli.*;
import classpath.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by yin on 18/4/2.
 */
public class Main {
    public static void main(String[] args) throws ParseException {
        CommandLine cmd = Cmd.cmd(args);


        if (cmd.getOptions().length == 0) {
            Cmd.printUsage(args);
        }

        if (cmd.hasOption("version")) {
            System.out.println("version 0.0.1");
        }
        if (cmd.hasOption("help")) {
            Cmd.printUsage(args);
        }

        else if (args.length >= 2) {
            startJVM(cmd, args);
            startJVM02(cmd, args);
        }
    }

    static void startJVM(CommandLine cmd, String[] args) {
        String jreOption = "";
        String cpOption = "";
        ClassPath cp;
        if (cmd.hasOption("cp")) {
            cpOption = cmd.getOptionValue("cp");
        }
        if (cmd.hasOption("Xjre")) {
            jreOption = cmd.getOptionValue("Xjre");
        }
        if (args.length == 2) {
            jreOption = "";
            cpOption = "";
        }
        cp = ClassPath.parse(jreOption, cpOption);
        String className = args[args.length-1].replace('.', File.separator.charAt(0));
        byte[] classData = null;
        try {
            classData = cp.readClass(className);
        } catch (IOException e) {
            System.out.printf("不能找到和加载主类 %s\n", args[args.length-1]);
        }
        for (int i = 0; i < classData.length; i++) {
            if (i % 20 == 0) {
                System.out.println();
            }
            System.out.printf("%02X ", classData[i]); // 此打印如《深入理解Java虚拟机》显示
        }
//        System.out.println();
    }

    // startJVM02用来测试ch03的代码
    static void startJVM02(CommandLine cmd, String[] args) {
        String jreOption = "";
        String cpOption = "";
        ClassPath cp;
        if (cmd.hasOption("cp")) {
            cpOption = cmd.getOptionValue("cp");
        }
        if (cmd.hasOption("Xjre")) {
            jreOption = cmd.getOptionValue("Xjre");
        }
        if (args.length == 2) {
            jreOption = "";
            cpOption = "";
        }

        cp = ClassPath.parse(jreOption, cpOption);
        String className = args[args.length-1].replace('.', File.separator.charAt(0));
        byte[] classData = null;
        try {
            classData = cp.readClass(className);
        } catch (IOException e) {
            System.out.printf("不能找到和加载主类 %s\n", args[args.length-1]);
        }

        ClassFile cf = ClassFile.Parse(classData);

        MemberInfo mainMethod = Interpreter.getMainMethod(cf);
        if (mainMethod != null) {
            new Interpreter().interpret(mainMethod);
        }

        System.out.println();
        System.out.println();
        System.out.println((cf.getFields()).length);
        System.out.println(cf.getMajorVersion());
        System.out.println(cf.getAccessFlags());
        System.out.println(cf.getClassName());
        System.out.println(cf.getSuperClassName());
        System.out.println(cf.getInterfaceNames().length);
        for (MemberInfo m: cf.getFields()) {
            System.out.println(m.getName());
        }
    }
}

