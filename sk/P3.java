package com.company;

public class P3 {

    public static int n, m;
    public static Pos[][] pos;

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static  int solution(int width, int height, int[][] diagonals) {
        int answer = 0;

        n = width + 1;
        m = height + 1;

        pos = new Pos[diagonals.length][2];

        for(int i = 0; i < diagonals.length; i++){
            pos[i][0] = new Pos(diagonals[i][0], diagonals[i][1] - 1);
            pos[i][1] = new Pos(diagonals[i][0] - 1, diagonals[i][1]);
        }

        long result = 0;
        for (Pos[] p : pos) {
            result += (start(p[0]) * end(p[1]) %  10000019);
            result += (start(p[1]) * end(p[0]) %  10000019);
        }

        result = result % 10000019;
        answer = (int)result;

        return answer;
    }

    public static long start(Pos p){
        int x = p.x;
        int y = p.y;

        long[][] map = new long[n][m];

        for(int i = 0; i <= x; i++) map[i][0] = 1;
        for(int i = 0; i <= y; i++) map[0][i] = 1;

        for(int i = 1; i <= x; i++){
            for(int j = 1; j <= y; j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }

        return map[x][y] % 10000019;
    }

    public static long end(Pos p){
        int x = p.x;
        int y = p.y;

        long[][] map = new long[n][m];

        for(int i = x; i < n; i++) map[i][y] = 1;
        for(int i = y; i < m; i++) map[x][i] = 1;

        for(int i = x+1; i < n; i++){
            for(int j = y+1; j < m; j++){
                map[i][j] = map[i-1][j] + map[i][j-1];
            }
        }

        return map[n-1][m-1] % 10000019;
    }

}
