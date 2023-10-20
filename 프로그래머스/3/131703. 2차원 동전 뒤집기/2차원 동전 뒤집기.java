class Solution {
    public static int n, m, ans;
    public static int[][] map, t;

    public static int solution(int[][] beginning, int[][] target) {
        ans = Integer.MAX_VALUE;
        n = beginning.length;
        m = beginning[0].length;

        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            map[i] = beginning[i].clone();
        }
        t = target;

        dfs(0, 0);

        if(ans == Integer.MAX_VALUE) return -1;
        return ans;
    }

    public static void dfs(int r, int cnt) {
        if(cnt >= ans) return;

        if(r == n) {
            boolean flag = true;

            for(int j = 0; j < m; j++) {
                int result = compareCol(j);
                if(result == -1) {
                    flag = false;
                    break;
                }
                cnt += result;
            }

            if(flag) {
                ans = Math.min(ans, cnt);
            }

            return;
        }

        flipRow(r);
        dfs(r+1, cnt+1);
        flipRow(r);

        dfs(r+1, cnt);
    }

    public static void flipRow(int r) {
        for(int j = 0; j < m; j++) {
            map[r][j] = map[r][j] == 1 ? 0 : 1;
        }
    }

    public static int compareCol(int c) {
        int cnt = 0;
        for(int i = 0; i < n; i++) {
            if(map[i][c] == t[i][c]) {
                cnt++;
            }
        }

        if(cnt == n) return 0;
        else if(cnt == 0) return 1;
        else return -1;
    }
}