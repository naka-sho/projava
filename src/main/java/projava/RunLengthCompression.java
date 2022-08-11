package main.java.projava;

public class RunLengthCompression {
    public static void main(String[] args) {
        final int COUNTER_BASE = -1;
        String data = "abbcccbaaabccccccccccddd";
        int count = COUNTER_BASE;
        char prev = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (char ch : data.toCharArray()) {
            if (ch == prev) {
                count++;
                if(count == 9){
                    stringBuffer.append('9');
                    count = COUNTER_BASE;
                    prev = 0;
                }
                continue;
            }

            if(count >= 0){
                stringBuffer.append((char) ('0' + count));
                count = COUNTER_BASE;
            }

            stringBuffer.append(ch);
            prev = ch;
        }

        if(count >= 0){
            stringBuffer.append((char) ('0' + count));
        }

        String s = stringBuffer.toString();
        System.out.println(data);
        System.out.println(s);
    }
}
