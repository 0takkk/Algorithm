import java.util.*;

class Solution {
    public static class Pos {
        int x, y;
        int move;
        String route = "";

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int move, String route) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.route = route;
        }
    }

    public static char[] comd = {'d', 'l', 'r', 'u'};
    public static int[] dx = {1, 0, 0, -1};
    public static int[] dy = {0, -1, 1, 0};


    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y));

        boolean[][][] visited = new boolean[2501][n+1][m+1];

        while(!q.isEmpty()) {
            Pos p = q.poll();

            if(p.x == r && p.y == c && p.move == k) {
                return p.route;
            }

            if(p.move >= k) continue;

            for(int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(!isRange(nx, ny, n, m)) continue;

                if(!visited[p.move][nx][ny]) {
                    visited[p.move][nx][ny] = true;
                    q.offer(new Pos(nx, ny, p.move+1, p.route+comd[i]));
                }
            }
        }

        return "impossible";
    }

    public static boolean isRange(int x, int y, int n, int m) {
        return x >= 1 && x <= n && y >= 1 && y <= m;
    }
}