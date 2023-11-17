class Solution {
    public static int n, answer;
    public static int[][] map, copy;
    public static int[] selected;

    public static int[] dx = {0, 0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0, 0};

    public static int solution(int[][] clockHands) {
        answer = Integer.MAX_VALUE;

        n = clockHands.length;
        map = new int[n][n];
        copy = new int[n][n];
        selected = new int[n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                map[i][j] = (4-clockHands[i][j]) % 4;
            }
        }

        dfs(0);
        return answer;
    }

    public static void dfs(int depth) {
        if(depth == n) {
            for(int i = 0; i < n; i++) {
                copy[i] = map[i].clone();
            }

            int cnt = rotate();
            if(isDone()) {
                answer = Math.min(answer, cnt);
            }
            return;
        }


        for(int d = 0; d < 4; d++) {
            selected[depth] = d;
            dfs(depth+1);
        }
    }

    public static boolean isDone() {
        for(int j = 0; j < n; j++) {
            if(copy[n-1][j] != 0) return false;
        }
        return true;
    }

    public static int rotate() {
        int cnt = 0;
        for (int select : selected) {
            cnt += select;
        }

        for(int i = 0; i < n; i++) {
            rotate(0, i, selected[i]);
        }

        for(int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (copy[i - 1][j] > 0) {
                    cnt += copy[i - 1][j];
                    rotate(i, j, copy[i - 1][j]);
                }
            }
        }

        return cnt;
    }

    public static void rotate(int x, int y, int d) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;
            copy[nx][ny] = (copy[nx][ny] - d + 4) % 4;
        }
    }
    
    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}