import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static HashMap<Character, Pos> keyboard;
    public static HashSet<Character> leftKeys, rightKeys;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        makeKeyBoard();
        makeLeft();
        makeRight();

        st = new StringTokenizer(br.readLine());
        Pos left = keyboard.get(st.nextToken().charAt(0));
        Pos right = keyboard.get(st.nextToken().charAt(0));

        String target = br.readLine();
        int ans = 0;

        for(int i = 0; i < target.length(); i++) {
            char now = target.charAt(i);
            Pos pos = keyboard.get(now);

            if(leftKeys.contains(now)) {
                ans += (Math.abs(left.x-pos.x) + Math.abs(left.y-pos.y) + 1);
                left = pos;
            }
            else {
                ans += (Math.abs(right.x-pos.x) + Math.abs(right.y-pos.y) + 1);
                right = pos;
            }
        }

        System.out.println(ans);
    }

    public static void makeLeft() {
        leftKeys = new HashSet<>(
                List.of(
                        'q', 'w', 'e', 'r', 't',
                        'a', 's', 'd', 'f', 'g',
                        'z', 'x', 'c', 'v'));
    }

    public static void makeRight() {
        rightKeys = new HashSet<>(
                List.of(
                        'y', 'u', 'i', 'o', 'p',
                        'h', 'j', 'k', 'l',
                        'b', 'n', 'm'
                ));
    }

    public static void makeKeyBoard() {
        keyboard = new HashMap<>();
        keyboard.put('q', new Pos(0, 0));
        keyboard.put('w', new Pos(0, 1));
        keyboard.put('e', new Pos(0, 2));
        keyboard.put('r', new Pos(0, 3));
        keyboard.put('t', new Pos(0, 4));
        keyboard.put('y', new Pos(0, 5));
        keyboard.put('u', new Pos(0, 6));
        keyboard.put('i', new Pos(0, 7));
        keyboard.put('o', new Pos(0, 8));
        keyboard.put('p', new Pos(0, 9));
        keyboard.put('a', new Pos(1, 0));
        keyboard.put('s', new Pos(1, 1));
        keyboard.put('d', new Pos(1, 2));
        keyboard.put('f', new Pos(1, 3));
        keyboard.put('g', new Pos(1, 4));
        keyboard.put('h', new Pos(1, 5));
        keyboard.put('j', new Pos(1, 6));
        keyboard.put('k', new Pos(1, 7));
        keyboard.put('l', new Pos(1, 8));
        keyboard.put('z', new Pos(2, 0));
        keyboard.put('x', new Pos(2, 1));
        keyboard.put('c', new Pos(2, 2));
        keyboard.put('v', new Pos(2, 3));
        keyboard.put('b', new Pos(2, 4));
        keyboard.put('n', new Pos(2, 5));
        keyboard.put('m', new Pos(2, 6));
    }

}