package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}}));
        System.out.println(solution(new int[][] {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}}));
    }

    public static class Pos{
        int x, y, time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int n, m, result;
    public static int[][] map;
    public static boolean flag;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(int[][] maps) {
        int answer = 0;

        n = maps.length;
        m = maps[0].length;

        map = new int[n+1][m+1];
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                map[i][j] = maps[i-1][j-1];
            }
        }

        bfs();

        if(flag) answer = result;
        else answer = -1;

        return answer;
    }

    public static void bfs(){
        boolean[][] visited = new boolean[n+1][m+1];

        if(map[n][m-1] == 0 && map[n-1][m] == 0){
            flag = false;
            return;
        }

        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(1, 1, 1));
        visited[1][1] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();

            int x = p.x;
            int y = p.y;
            int time = p.time;

            if(x == n && y == m){
                flag = true;
                result = time;
                return;
            }

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > m) continue;

                if(map[nx][ny] == 1 && !visited[nx][ny]){
                    q.offer(new Pos(nx, ny, time+1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

}
