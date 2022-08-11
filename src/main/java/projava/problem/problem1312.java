package main.java.projava.problem;

import java.util.ArrayList;
import java.util.List;

public class problem1312 {
    public static void main(String[] args) {
        String str = "aa0bcd1efg1gg0abc";

        char[] chars = str.toCharArray();
        List<String> strings = new ArrayList<>();

        int i = 0;
        boolean lower = true;
        for (char ch : chars) {

            String s = charToString(ch);

            if (!prevString(i)) {
                strings.add(s);
                i++;
                continue;
            }

            i++;

            if (lower(s)) {
                lower = true;
                continue;
            }

            if (upper(s)) {
                lower = false;
                continue;
            }

            strings.add(stringToLowerOrUpper(lower, s));
        }
        strings.stream().forEach(ee -> System.out.print(ee));
    }

    /**
     * 直前に文字列があるか
     *
     * @param i
     * @return
     */
    private static boolean prevString(int i) {
        return i > 0;
    }

    private static String charToString(char ch) {
        return String.valueOf(ch);
    }

    /**
     * 文字列0ならば、大文字に
     *
     * @param s
     * @return
     */
    private static boolean upper(String s) {
        return "0".equals(s);
    }

    /**
     * 文字列1ならば、小文字に
     *
     * @param s
     * @return
     */
    private static boolean lower(String s) {
        return "1".equals(s);
    }

    /**
     * 文字列を大文字 or 小文字にして返す
     *
     * @param isLower
     * @param s
     * @return
     */
    private static String stringToLowerOrUpper(boolean isLower, String s) {
        if (isLower) {
            return s.toLowerCase();
        }
        return s.toUpperCase();
    }
}
