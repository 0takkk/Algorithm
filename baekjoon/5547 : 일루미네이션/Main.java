package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int w, h;
    public static int[][] map;
    public static boolean[][] outdoor;

    public static int[] dx = {-1, -1, 0, 0, 1, 1};
    public static int[] dy_odd = {0, 1, -1, 1, 0, 1};
    public static int[] dy_even = {-1, 0, -1, 1, -1, 0};

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
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h+2][w+2];
        outdoor = new boolean[h+2][w+2];

        for(int i = 1; i < h+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < w+1; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeOutdoor();

        int ans = getLength();
        System.out.println(ans);
    }

    public static int getLength(){
        int result = 0;

        for(int i = 1; i < h+1; i++){
            for(int j = 1; j < w+1; j++){
                if(map[i][j] == 0) continue;

                for(int k = 0; k < 6; k++){
                    int x = i + dx[k];
                    int y = (i % 2 == 1) ? j + dy_odd[k] : j + dy_even[k];

                    if(x < 0 || x >= h+2 || y < 0 || y >= w+2) continue;

                    if(outdoor[x][y]) result++;
                }
            }
        }

        return result;
    }

    public static void makeOutdoor(){
        Queue<Pos> q = new LinkedList<>();
        q.offer(new Pos(0, 0));
        outdoor[0][0] = true;

        while(!q.isEmpty()){
            Pos p = q.poll();

            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 6; i++){
                int nx = x + dx[i];
                int ny = (x % 2 == 1) ? y + dy_odd[i] : y + dy_even[i];

                if(nx < 0 || nx >= h+2 || ny < 0 || ny >= w+2) continue;
                if(outdoor[nx][ny]) continue;

                if(map[nx][ny] == 0){
                    outdoor[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }
    }

}
