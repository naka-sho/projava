package main.java.projava.problem;

import java.util.ArrayList;
import java.util.List;

public class problem13122 {
    public static void main(String[] args) {
        String str = "ab0c1ba2bc9cd1";

        char[] chars = str.toCharArray();

        String prevStr = "";
        List<String> arrayList = new ArrayList();
        for (char ch: chars){
            String s = charToString(ch);

            if(!isNum(s)){
                prevStr = s;
                arrayList.add(s);
                continue;
            }

            int letterCount = letterCount(s);
            String expansion = expansion(letterCount, prevStr);
            arrayList.add(expansion);
        }

        arrayList.stream().forEach(e -> System.out.print(e));

    }

    private static boolean isNum(String str){
        try {
            Integer.parseInt(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 展開する文字数を返却する
     *
     * @param str
     * @return
     */
    private static int letterCount(String str){
        try {
            return Integer.parseInt(str) + 1;
        }catch (Exception e){
            return 0;
        }
    }

    private static String expansion(int count, String s){
        String formater = "%" + count + "s";
        return String
                .format(formater, "")
                .replace(" ",s);
    }

    private static String charToString(char ch){
        return  String.valueOf(ch);
    }
}
