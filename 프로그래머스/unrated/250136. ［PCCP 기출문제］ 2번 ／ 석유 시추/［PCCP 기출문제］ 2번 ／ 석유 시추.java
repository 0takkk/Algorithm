import java.util.*;

class Solution {
    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited;
    public static int[] count;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(int[][] land) {
        int answer = 0;

        n = land.length;
        m = land[0].length;
        visited = new boolean[n][m];
        map = land;
        count = new int[m];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1 && !visited[i][j]) {
                    bfs(new Pos(i, j));
                }
            }
        }

        for(int y = 0; y < m; y++) {
            answer = Math.max(answer, count[y]);
        }

        return answer;
    }

    public static void bfs(Pos pos) {
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;
        int cnt = 1;
        HashSet<Integer> set = new HashSet<>();
        set.add(pos.y);

        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(isRange(nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    cnt++;
                    q.offer(new Pos(nx, ny));
                    set.add(ny);
                }
            }
        }

        for (int y : set) {
            count[y] += cnt;
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}