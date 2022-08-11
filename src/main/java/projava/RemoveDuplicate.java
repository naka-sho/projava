package main.java.projava;

public class RemoveDuplicate
{
    public static void main(String[] args) {
        String str = "abcccbaabc";
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(i > 0 && c == str.charAt(i -1)){
                continue;
            }
            stringBuffer.append(c);
        }

        String s = stringBuffer.toString();
        System.out.println(str);
        System.out.println(s);
    }
}
