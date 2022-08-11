package main.java.projava;

public class CheckFloat {

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
    }

    enum FloatState {
        START,
        INT,
        FRAC_START,
        FRAC,
        ZERO
    }

    private static boolean check(String data) {
        FloatState state = FloatState.START;
        for (char ch : data.toCharArray()) {
            switch (state) {
                case START -> {
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
}
