package main.java.projava.problem;

public class problem13212 {

    public static void main(String[] args) {
        System.out.println(check(""));
        System.out.println(check("012"));
        System.out.println(check(".12"));
        System.out.println(check("12."));
        System.out.println(check("1.2.3"));
        System.out.println(check("1..3"));
        System.out.println(check("0"));
        System.out.println(check("12"));
        System.out.println(check("12.3"));
        System.out.println(check("0.3"));
        System.out.println(check("12.30"));
        System.out.println(check("12.0"));
        System.out.println(check("-123"));
        System.out.println(check("--123"));
        System.out.println(check("-12-3"));
    }

    enum FloatState {
        /**
         * 開始
         */
        START,
        /**
         * 符号
         */
        SIGNED,
        /**
         * 整数部位
         */
        INT,
        /**
         * 小数点開始
         */
        FRAC_START,
        /**
         * 小数点の中
         */
        FRAC,
        /**
         * 小数点の最後
         */
        FRAC_END,
        /**
         * ゼロ
         */
        ZERO
    }

    private static boolean check(String data) {
        FloatState state = FloatState.START;
        int i = 0;
        int length = data.toCharArray().length;
        for (char ch : data.toCharArray()) {
            i++;
            switch (state) {
                case START -> {
                    if (isSigned(ch)){
                        state = FloatState.SIGNED;
                        break;
                    }

                    if (isZero(ch)) {
                        state = FloatState.ZERO;
                        break;
                    }

                    if(isNum(ch)){
                        state = FloatState.INT;
                        break;
                    }
                    return false;
                }

                case SIGNED -> {
                    if (isZero(ch)) {
                        state = FloatState.ZERO;
                        break;
                    }

                    if(isNum(ch)){
                        state = FloatState.INT;
                        break;
                    }
                    return false;
                }

                case ZERO -> {
                    if (isDot(ch)){
                        state = FloatState.FRAC_START;
                        break;
                    }

                    return false;
                }

                case INT -> {
                    if(isNum(ch)){
                        state = FloatState.INT;
                        break;
                    }

                    if (isDot(ch)){
                        state = FloatState.FRAC_START;
                        break;
                    }

                    return false;

                }

                case FRAC_START, FRAC -> {
                    if(isZero(ch) && i == length){
                        state = FloatState.FRAC_END;
                        break;
                    }

                    if(isNum(ch)){
                        state = FloatState.FRAC;
                        break;
                    }

                    return false;
                }
            }
        }
        return switch (state) {
            case ZERO, INT, FRAC -> true;
            default -> false;
        };
    }

    private static String charToStr(char ch){
        return String.valueOf(ch);
    }

    private static boolean isNum(char ch){
        try {
            Integer.parseInt(charToStr(ch));
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    private static boolean isZero(char ch){
        return "0".equals(charToStr(ch));
    }

    private static boolean isDot(char ch){
        return ".".equals(charToStr(ch));
    }

    private static boolean isSigned(char ch){
        return "-".equals(charToStr(ch));
    }
}
