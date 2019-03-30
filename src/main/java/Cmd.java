import org.apache.commons.cli.*;

/**
 * Created by cgrw on 18/4/2.
 */
class Cmd {
    static CommandLine cmd(String[] args) throws ParseException {
        Options options = new Options();
        options.addOption("help", false, "print help message");
        options.addOption("?", false, "print help message");
        options.addOption("version", false, "print version and exit");
        options.addOption("Xjre", true, "path to jre");
        options.addOption("cp", "classpath", true, "classpath");
        options.addOption("v", "verbose", true, "verbose");
        //解析
        CommandLineParser paraer = new DefaultParser();
        return paraer.parse(options, args);
    }

    static void printUsage() {
        System.out.println("Usage: java -jar pip-boy.jar [-options <params...>] class [args...]");
    }
}

