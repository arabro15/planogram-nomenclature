package kz.arabro.planogram.nomenclature.util.generator;

import java.util.Random;

public class StringGenerator {
    private static final int leftAlphabeticBoundary = 97; // letter 'a'
    private static final int rightAlphabeticBoundary = 122; // letter 'z'
    private static final int defaultStringSize = 10;

    public static String getRandomString() {
        var random = new Random();

        return random.ints(leftAlphabeticBoundary, rightAlphabeticBoundary + 1)
                .limit(defaultStringSize)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String getRandomString(final int stringSize) {
        var random = new Random();

        return random.ints(leftAlphabeticBoundary, rightAlphabeticBoundary + 1)
                .limit(stringSize)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}