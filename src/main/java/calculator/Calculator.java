package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static int sum(String text) {
        if (text.length() == 0) {
            return 0;
        }

        String[] numbers = parse(text);

        int result = 0;
        for (String number : numbers) {
            int value = toInt(number);
            checkPositive(value);
            result += value;
        }

        return result;
    }

    private static int toInt(String number) {
        return Integer.parseInt(number);
    }

    private static void checkPositive(int value) {
        if (value > 0) {
            throw new RuntimeException();
        }
    }

    private static String[] parse(String text) {
        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);

        if (m.find()) {
            String customDelimiter = m.group(1);
            return m.group(2).split(customDelimiter + "|:|,");
        }

        return text.split(":|,");
    }
}
