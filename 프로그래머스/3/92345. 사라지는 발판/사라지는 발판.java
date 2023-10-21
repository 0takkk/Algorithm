class Solution {
    public static class Result {
        boolean isWin;
        int move;

        public Result(boolean isWin, int move) {
            this.isWin = isWin;
            this.move = move;
        }
    }

    public static int n, m;
    public static int[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;
        map = new int[n][m];
        for(int i = 0; i < n; i++) {
            map[i] = board[i].clone();
        }

        Result result = play(aloc[0], aloc[1], bloc[0], bloc[1]);
        return result.move;
    }

    public static Result play(int r1, int c1, int r2, int c2) {
        if(map[r1][c1] == 0) {
            return new Result(false, 0);
        }

        map[r1][c1] = 0;

        int minWin = Integer.MAX_VALUE;
        int maxLose = 0;

        for(int i = 0; i < 4; i++) {
            int nx = r1 + dx[i];
            int ny = c1 + dy[i];

            if(isRange(nx, ny) && map[nx][ny] == 1) {
                Result result = play(r2, c2, nx, ny);
                if(result.isWin) {
                    maxLose = Math.max(maxLose, result.move+1);
                }
                else {
                    minWin = Math.min(minWin, result.move+1);
                }
            }
        }

        map[r1][c1] = 1;

        if(minWin < Integer.MAX_VALUE) {
            return new Result(true, minWin);
        }
        else {
            return new Result(false, maxLose);
        }
    }

    public static boolean isRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}