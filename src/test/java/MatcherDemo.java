import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {

    public static void main(String[] args) {
        String paramsString = null;

        String descriptor = "(IILjava/lang/String;LClassFileDemo;)Ljava/lang/String;";
//        Pattern pattern = Pattern.compile("(?<=\\()[^)]+");

        Pattern pattern = Pattern.compile("(?<=\\().*(?=\\))");
        Matcher matcher = pattern.matcher(descriptor);
        if (matcher.find()) {
            paramsString = matcher.group();
        }

        Pattern pattern1 = Pattern.compile("L.*?;");
        Matcher matcher1 = pattern1.matcher(paramsString);

        int count = 0;
        while (matcher1.find()) {
            count++;
        }

//        paramsString = Pattern.compile("(?<=L).*(?=;)").matcher(paramsString).group();
        System.out.println(count);
        System.out.println(matcher1.replaceAll(""));
    }
}
