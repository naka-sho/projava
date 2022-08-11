package main.java.projava;

public class RemoveDuplicate2
{
    public static void main(String[] args) {
        char prev = 0;

        String str = "abcccbaabc";
        StringBuffer stringBuffer = new StringBuffer();

        for (char ch: str.toCharArray()){
            if (ch == prev){
                continue;
            }

            stringBuffer.append(ch);
            prev = ch;
        }

        String s = stringBuffer.toString();
        System.out.println(str);
        System.out.println(s);
    }
}
