package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static class RGB{
        int r, g, b;

        public RGB(int r, int g, int b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }

    public static int n, result = Integer.MAX_VALUE;
    public static RGB list[], target;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        list = new RGB[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[i] = new RGB(r, g, b);
        }

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        target = new RGB(r, g, b);

        visited = new boolean[n];
        dfs(new RGB(0, 0, 0), 0, 0);

        System.out.println(result);
    }

    public static void dfs(RGB rgb, int idx, int cnt){
        if(cnt >= 2){
            int r = rgb.r / cnt;
            int g = rgb.g / cnt;
            int b = rgb.b / cnt;

            result = Math.min(result, Math.abs(r - target.r) + Math.abs(g - target.g) + Math.abs(b - target.b));
        }

        for(int i = idx; i < n; i++){
            if(!visited[i] && cnt <= 6){
                visited[i] = true;
                dfs(new RGB(rgb.r + list[i].r, rgb.g + list[i].g, rgb.b + list[i].b), idx+1, cnt+1);
                visited[i] = false;
            }
        }
    }

}
