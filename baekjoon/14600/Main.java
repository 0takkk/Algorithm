package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int N, X, Y;
    public static int[][] map;
    public static int num;

    public static int[] dx = {1,0,0,1,-1,0,-1,0};
    public static int[] dy = {0,1,-1,0,0,1,0,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int k = Integer.parseInt(br.readLine());
        N = (int) Math.pow(2, k);

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        map[N-Y+1][X] = -1;

        solution(1, 1, N);

        for(int i = 1; i<= N; i++){
            for(int j  = 1; j <= N; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void solution(int x, int y, int size){
        num++;
        int ns = size/2;
        if(check(x, y, ns)) map[x+ns-1][y+ns-1] = num;
        if(check(x+ns, y, ns)) map[x+ns][y+ns-1] = num;
        if(check(x, y+ns, ns)) map[x+ns-1][y+ns] = num;
        if(check(x+ns, y+ns, ns)) map[x+ns][y+ns] = num;

        if(size == 2) return;

        solution(x, y, ns);
        solution(x+ns, y, ns);
        solution(x, y+ns, ns);
        solution(x+ns, y+ns, ns);
    }

    public static boolean check(int x, int y, int size){
        for(int i = x; i < x+size; i++)
            for(int j = y; j < y+size; j++)
                if(map[i][j] != 0) return false;

        return true;
    }

}
