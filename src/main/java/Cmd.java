import org.apache.commons.cli.*;

/**
 * Created by yin on 18/4/2.
 */
class Cmd {
    public static CommandLine cmd(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help", false, "print help message");
        options.addOption("?", false, "print help message");
        options.addOption("version", false, "print version and exit");
        options.addOption("Xjre", true, "path to jre");
        options.addOption("cp", "classpath", true, "classpath");
        //解析
        CommandLineParser paraer = new DefaultParser();
        CommandLine cmd = paraer.parse(options, args);
        return cmd;
    }

    public static void printUsage(String[] args) {
        if (args.length > 0) {
            System.out.printf("Usage: %s [-options] class [args...]\n", args[0]);
        }
        else {
            System.out.println("Usage: arg [-options] class [args...]");
        }
    }
}

