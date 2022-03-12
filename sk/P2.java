package com.company;

import java.util.LinkedList;
import java.util.Queue;

public class P2 {

    public static int[] rdx = {0, 1, 0, -1};
    public static int[] rdy = {1, 0, -1, 0};
    public static int[] ldx = {1, 0, -1, 0};
    public static int[] ldy = {0, -1, 0, 1};
    public static int[][] answer;
    public static int N;

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static  int[][] solution(int n, boolean clockwise) {
        answer = new int[n][n];
        N = n;

        dfs(clockwise);

        for(int i = 0; i < N ; i++){
            for(int j = 0; j < N; j++){
                System.out.print(answer[i][j] + " ");
            }
            System.out.println();
        }

        return answer;
    }

    public static void dfs(boolean clockwise){
        boolean[][] visited = new boolean[N][N];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0));
        q.offer(new Pos(0, N-1));
        q.offer(new Pos(N-1, N-1));
        q.offer(new Pos(N-1, 0));

        int dir = 0;
        int idx = 1;

        int[] dx, dy;

        while(!q.isEmpty()){
            int size = q.size();

            for(int k = 0; k < size; k++) {
                Pos p = q.poll();
                if(!visited[p.x][p.y])
                    answer[p.x][p.y] = idx;
                visited[p.x][p.y] = true;

                if (clockwise) {
                    dx = rdx;
                    dy = rdy;
                }
                else{
                    dx = ldx;
                    dy = ldy;
                }

                int nx = p.x + dx[(k + dir + 4) % 4];
                int ny = p.y + dy[(k + dir + 4) % 4];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (visited[nx][ny]) {
                    if(clockwise) dir++;
                    else {
                        dir--;
                        if(dir == -5) dir = 0;
                    }
                    nx = p.x + dx[(k + dir + 4) % 4];
                    ny = p.y + dy[(k + dir + 4) % 4];
                }

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(!visited[nx][ny])
                    q.offer(new Pos(nx, ny));

            }
            idx++;
        }
        
    }
    
}
