package fr.lunastia.skyblock.core.utils;

import java.text.DecimalFormat;

public class TextUtils {
    public static String toRomain(Integer level) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] romanLiterals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder roman = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            while (level >= values[i]) {
                level -= values[i];
                roman.append(romanLiterals[i]);
            }
        }
        return roman.toString();
    }
}
