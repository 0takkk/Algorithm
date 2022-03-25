package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, map[][];
    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

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

        n = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }

    public static void bfs(){
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(1, 1));
        visited[1][1] = true;

        while(!q.isEmpty()){

            int size = q.size();
            for(int i = 0; i < size; i++){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;
                int num = map[x][y];

                if(x == n && y == n){
                    System.out.println("HaruHaru");
                    return;
                }


                for(int j = 0; j < 2; j++){
                    int nx = x + num * dx[j];
                    int ny = y + num * dy[j];

                    if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

                    if(!visited[nx][ny]){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }

            }

        }

        System.out.println("Hing");
    }

}
