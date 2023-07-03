package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    public static String[] parse(String text) {
        if (text.isEmpty()) {
            return new String[]{""};
        }

        Matcher m = Pattern.compile("//(.)\n(.*)").matcher(text);

        String[] result = text.split(":|,");
        if (m.find()) {
            String customDelimiter = m.group(1);
            result = m.group(2).split(customDelimiter);
        }

        for (String value : result) {
            validate(value);
        }

        return result;
    }

    public static int sum(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        String[] numbers = parse(text);

        int result = 0;
        for (String number : numbers) {
            result += toInt(number);
        }

        return result;
    }

    private static void validate(String value) {
        int result;
        try {
            result = toInt(value);
        } catch (NumberFormatException e) {
            throw new RuntimeException("숫자가 아닙니다.");
        }

        if (result < 0) {
            throw new RuntimeException("음수는 입력할 수 없습니다.");
        }
    }

    private static int toInt(String number) {
        return Integer.parseInt(number);
    }
}
