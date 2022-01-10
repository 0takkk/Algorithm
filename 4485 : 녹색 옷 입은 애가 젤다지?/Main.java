package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int map[][], distances[][];

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos{
        int x, y;

        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 1;
        
        while(true){
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            map = new int[n][n];
            distances = new int[n][n];

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }

            distances[0][0] = map[0][0];

            bfs();
            System.out.println("Problem " + t++ + ": " + distances[n-1][n-1]);
        }
    }

    public static void bfs(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0,0));

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= n) continue;
                if(distances[nx][ny] <= distances[x][y] + map[nx][ny]) continue;

                distances[nx][ny] = distances[x][y] + map[nx][ny];
                q.offer(new Pos(nx, ny));
            }
        }
    }
    
}
