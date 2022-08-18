package main.java.projava;

import java.util.ArrayDeque;

public class TraverseDeep {
    private static final int BLOCK = 1;

    private static final int STREET = 0;

    private static final int GOAL = 2;

    private static final int MOVED = 3;

    public static void main(String[] args) {
        int[][] map = {
                {BLOCK, BLOCK, BLOCK, BLOCK, BLOCK, BLOCK, BLOCK},
                {BLOCK, STREET, BLOCK, STREET, STREET, STREET, BLOCK},
                {BLOCK, STREET, STREET, STREET, BLOCK, BLOCK, BLOCK},
                {BLOCK, STREET, BLOCK, STREET, STREET, GOAL, BLOCK},
                {BLOCK, BLOCK, BLOCK, BLOCK, BLOCK, BLOCK, BLOCK}
        };

        traverse(map, 1, 1);

        char[] ch = {'.', '*', 'G', 'o'};
        for (int row[] : map) {
            for (int cell : row) {
                System.out.print(ch[cell]);
            }
            System.out.println();
        }


    }

    record Position(int x, int y) {
    }

    ;

    static boolean traverse(int[][] map, int curX, int curY) {

        ArrayDeque<Position> stack = new ArrayDeque<>();
        stack.push(new Position(curX, curY));

        for (Position position; (position = stack.pollLast()) != null; ) {

            if (map[position.x][position.y] == GOAL) {
                return true;
            }

            if (map[position.x][position.y] != STREET) {
                continue;
            }

            map[position.x][position.y] = MOVED;

            stackLeft(stack,position.x,position.y);
            stackRight(stack,position.x,position.y);
            stackUp(stack,position.x,position.y);
            stackDown(stack,position.x,position.y);
        }
        return false;

    }

    /**
     * 上下左右に動く
     *
     * @param map
     * @param curX
     * @param curY
     * @return
     */
    private static boolean move(int[][] map, int curX, int curY) {
        return left(map, curX, curY) ||
                right(map, curX, curY) ||
                up(map, curX, curY) ||
                down(map, curX, curY);
    }

    private static boolean left(int[][] map, int curX, int curY) {
        return traverse(map, curX + 1, curY);
    }

    private static boolean right(int[][] map, int curX, int curY) {
        return traverse(map, curX - 1, curY);
    }

    private static boolean up(int[][] map, int curX, int curY) {
        return traverse(map, curX, curY + 1);
    }

    private static boolean down(int[][] map, int curX, int curY) {
        return traverse(map, curX, curY - 1);
    }

    private static ArrayDeque<Position> stackLeft(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX + 1, curY));
        return stack;
    }

    private static ArrayDeque<Position> stackRight(ArrayDeque<Position> stack, int curX, int curY) {
        stack.push(new Position(curX - 1, curY));
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
}
