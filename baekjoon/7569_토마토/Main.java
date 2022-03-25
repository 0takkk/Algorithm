package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int z, x, y;

        public Pos(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    public static int m, n, h, time;
    public static int[][][] box;
    public static Queue<Pos> ripeTomato = new LinkedList<>();

    public static int[] dz = {0, 0, 0, 0, 1, -1};
    public static int[] dx = {0, 0, 1, -1, 0, 0};
    public static int[] dy = {1, -1, 0, 0, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        box = new int[h][n][m];

        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k = 0; k < m; k++){
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if(box[i][j][k] == 1){
                        ripeTomato.offer(new Pos(i,j,k));
                    }
                }
            }
        }

        bfs();

        if(time == -1) System.out.println(time);
        else if(time == 1) System.out.println(0);
        else System.out.println(time-1);
    }

    public static void bfs(){

        while(!ripeTomato.isEmpty()){
            Pos p = ripeTomato.poll();

            int z = p.z;
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 6; i++){
                int nz = z + dz[i];
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nz < 0 || nz >= h || nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(box[nz][nx][ny] == 0){
                    ripeTomato.offer(new Pos(nz, nx, ny));
                    box[nz][nx][ny] = box[z][x][y] + 1;
                }
            }
        }

        for(int i = 0; i < h; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < m; k++){
                    if(box[i][j][k] == 0){
                        time = -1;
                        return;
                    }
                    time = Math.max(time, box[i][j][k]);
                }
            }
        }


    }

}
