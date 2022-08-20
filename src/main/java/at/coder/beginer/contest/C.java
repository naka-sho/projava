package main.java.at.coder.beginer.contest;

import java.util.*;
import java.util.stream.Collectors;

public class C {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int r = sc.nextInt();
        int c = sc.nextInt();
        int sy = sc.nextInt() - 1;
        int sx = sc.nextInt() - 1;
        int gy = sc.nextInt() - 1;
        int gx = sc.nextInt() - 1;

        List<List<Integer>> map = new ArrayList<>();

        for (int y = 0; y < r; y++) {
            List<Integer> x = new ArrayList<>();
            String s = sc.next();
            int i = 0;
            for (char ch : s.toCharArray()) {
                if(y == gy && i == gx){
                    x.add(Block.strToNum(Block.GOAL.getStr()));
                    i++;
                    continue;
                }

                x.add(Block.strToNum(charToStr(ch)));
                i++;
            }
            map.add(x);
        }

//        map.stream().forEach(
//                e -> {
//                    System.out.print(e);
//                    System.out.println();
//                }
//        );

        int move = 0;
        traverse(map, sx , sy, move);

//        map.stream().forEach(
//                e -> {
//                    System.out.print(e);
//                    System.out.println();
//                }
//        );
    }

    private static List<List<Integer>> traverse(List<List<Integer>> map, int curX, int curY, int move){
        ArrayDeque<Position> stack = new ArrayDeque();
        stack.push(new Position(curX,curY));
        Position position ;
        while ((position = stack.pollLast()) != null) {
            if(map.get(position.y()).get(position.x()) == Block.GOAL.getNum()){
                System.out.println(minMove(map,position) - Block.MOVED.getNum() - 1);
                return map;
            }

            if(map.get(position.y()).get(position.x()) != Block.STREET.getNum()){
                continue;
            }

            map.get(position.y()).set(position.x(), minMove(map,position));

            stackLeft(stack,position.x(),position.y());
            stackRight(stack,position.x(),position.y());
            stackUp(stack,position.x(),position.y());
            stackDown(stack,position.x(),position.y());
        }

        return map;
    }


    private static int minMove(List<List<Integer>> map, Position position){
        Integer integer = List.of(
                        map.get(position.y() + 1).get(position.x()),
                        map.get(position.y() - 1).get(position.x()),
                        map.get(position.y()).get(position.x() + 1),
                        map.get(position.y()).get(position.x() - 1)
                )
                .stream()
                .filter(e -> e > Block.MOVED.getNum())
                .min(Comparator.naturalOrder())
                .orElse(Block.MOVED.getNum())
                ;
        return integer.intValue() + 1;
    }

    private static ArrayDeque<Position> stackRight(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX - 1, curY));
        return stack;
    }

    private static ArrayDeque<Position> stackLeft(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX + 1, curY));
        return stack;
    }

    private static ArrayDeque<Position> stackUp(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX, curY + 1));
        return stack;
    }

    private static ArrayDeque<Position> stackDown(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX, curY - 1));
        return stack;
    }

    private static String charToStr(char ch) {
        return String.valueOf(ch);
    }
}

class Position {
    private final int x;
    private final int y;

    Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }
}


enum Block {
    BLOCK(1, "#"),
    STREET(0, "."),
    GOAL(2, "G"),
    MOVED(10, "m");

    private final int num;

    private final String str;

    Block(int num, String str) {
        this.num = num;
        this.str = str;
    }

    public int getNum() {
        return num;
    }

    public String getStr() {
        return str;
    }

    static Map<String, Block> map = Arrays
            .stream(Block.values())
            .collect(Collectors.toMap(e -> e.str, e -> e));

    public static int strToNum(String str) {
        return map.getOrDefault(str, Block.BLOCK).num;
    }
}