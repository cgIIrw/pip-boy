import org.apache.commons.cli.*;

/**
 * Created by yin on 18/4/2.
 */
class Cmd {
    static CommandLine cmd(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help", false, "print help message");
        options.addOption("?", false, "print help message");
        options.addOption("version", false, "print version and exit");
        options.addOption("Xjre", true, "path to jre");
        options.addOption("cp", "classpath", true, "classpath");
        //解析
        CommandLineParser paraer = new DefaultParser();
        return paraer.parse(options, args);
    }

    static void printUsage(String className) {
        System.out.println("Usage: java " + className + " [-options] class [args...]");
    }
}

