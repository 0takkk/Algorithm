import java.io.*;
import java.util.*;

public class Main {

    public static class Pos {
        int x, y, num;
        char op;

        public Pos(int x, int y, int num, char op) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.op = op;
        }
    }
    public static int n, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    public static char[][] map;

    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new char[n][n];

        for(int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            for(int j = 0; j < n; j++) {
                map[i][j] = str[j].charAt(0);
            }
        }

        bfs();

        System.out.println(max + " " + min);
    }

    public static void bfs() {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(0, 0, map[0][0]-'0', ' '));

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int num = p.num;

            if(x == n-1 && y == n-1) {
                max = Math.max(max, num);
                min = Math.min(min, num);
                continue;
            }

            for(int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(isNumber(map[nx][ny])) {
                    int newNum = calc(num, p.op, map[nx][ny]-'0');
                    q.offer(new Pos(nx, ny, newNum, ' '));
                }
                else {
                    q.offer(new Pos(nx, ny, num, map[nx][ny]));
                }
            }
        }

    }

    public static boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    public static int calc(int num1, char op, int num2) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            default:
                return num1 * num2;
        }
    }

    public static boolean isNumber(char num) {
        return '0' <= num && num <= '9';
    }

}
