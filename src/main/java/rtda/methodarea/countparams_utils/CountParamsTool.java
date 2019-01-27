package rtda.methodarea.countparams_utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountParamsTool {

    public static int countMethod(String descriptor) {
        String paramsString = null;

//       test：String descriptor = "(IILjava/lang/String;LClassFileDemo;)Ljava/lang/String;";
        // 贪婪式
        Pattern pattern = Pattern.compile("(?<=\\().*(?=\\))");
        Matcher matcher = pattern.matcher(descriptor);
        if (matcher.find()) {
            // 计算出传入描述符字符串括号内的子串
            paramsString = matcher.group();
        }
        if (paramsString == null || paramsString.equals("")) {
            return 0;
        }

        // 非贪婪式
        Pattern pattern1 = Pattern.compile("L.*?;");
        Matcher matcher1 = pattern1.matcher(paramsString);

        // 引用类参数的个数
        int refCount = 0;
        while (matcher1.find()) {
            refCount++;
        }

        // 非引用类型部分的参数
        String nonRefPart = matcher1.replaceAll("");

        int nonRefCount = 0;
        char[] chars = nonRefPart.toCharArray();
        for (char c : chars) {
            switch (c) {
                case 'B':
                case 'C':
                case 'F':
                case 'I':
                case 'S':
                case 'Z':
                    nonRefCount++;
                    break;
                case 'D':
                case 'J':
                    nonRefCount += 2;
                    break;
                default:
                    break;
            }
        }
        return refCount + nonRefCount;
    }
}
