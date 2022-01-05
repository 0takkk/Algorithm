package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[][] map;
    public static boolean[][] visited;
    public static int min = Integer.MAX_VALUE;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(min);
    }

    public static void dfs(int count, int sum){
        if(count == 3){
            min = Math.min(min, sum);
            return;
        }

        for(int i = 1; i < n-1; i++){
            for(int j = 1; j < n-1; j++){
                if(!visited[i][j] && check(i, j)){
                    visited[i][j] = true;
                    int bloomSum = bloom(i, j);

                    dfs(count + 1, sum + bloomSum);

                    visited[i][j] = false;
                    clear(i, j);
                }
            }
        }

    }

    public static boolean check(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(visited[nx][ny]) return false;
        }
        return true;
    }

    public static int bloom(int x, int y){
        int sum = map[x][y];
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            sum += map[nx][ny];
            visited[nx][ny] = true;
        }

        return sum;
    }

    public static void clear(int x, int y){
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            visited[nx][ny] = false;
        }
    }

}
