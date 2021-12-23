package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int[][] score;
    public static boolean[] visited;
    public static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int c = Integer.parseInt(br.readLine());

        while(c-->0){
            max = Integer.MIN_VALUE;
            visited = new boolean[11];
            score = new int[11][11];

            for(int i = 0; i < 11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++){
                    score[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0,0);
            System.out.println(max);
        }
    }

    public static void dfs(int idx, int total){
        if(idx == 11){
            max = Math.max(max, total);
            return;
        }

        for(int i = 0; i < 11; i++){
            if(!visited[i] && score[idx][i] != 0){
                visited[i] = true;
                dfs(idx+1, total + score[idx][i]);
                visited[i] = false;
            }
        }
    }


}
