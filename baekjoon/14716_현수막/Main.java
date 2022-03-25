package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, result;
    public static int[][] board;
    public static boolean[][] visited;

    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && board[i][j] == 1){
                    dfs(new Pos(i, j));
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    public static void dfs(Pos pos){
        int x = pos.x;
        int y = pos.y;

        visited[x][y] = true;

        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

            if(!visited[nx][ny] && board[nx][ny] == 1){
                dfs(new Pos(nx, ny));
            }
        }
    }

}
