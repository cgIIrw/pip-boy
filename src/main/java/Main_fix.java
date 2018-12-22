import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

public class Main_fix {

    public static void main(String[] args) throws ParseException {
        String className = getMainName();

        CommandLine cmd = Cmd.cmd(args);

        if (cmd.hasOption("version")) {
            System.out.println("version 0.0.1");
        } else if (cmd.hasOption("help") || cmd.hasOption("?") || cmd.getOptions().length == 0) {
            Cmd.printUsage(className);
        } else {
            startJVM();
        }
    }

    private static void startJVM() {
        System.out.println("Hello JVM!");
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
