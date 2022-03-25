package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, h;
    public static boolean[][] map;
    public static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        int result = Integer.MAX_VALUE;
        map = new boolean[h+1][n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = true;
        }

        if(m == 0) result = 0;
        else{
            for(int i = 0; i <= 3; i++){
                isPossible = false;
                dfs(0, i);

                if(isPossible){
                    result = i;
                    break;
                }
            }
        }

        if(result > 3) result = -1;

        System.out.println(result);
    }

    public static void dfs(int count, int max){
        if(isPossible) return;
        
        if(count == max){
            if(check()) isPossible = true;
            return;
        }

        for(int i = 1; i <= h; i++){
            for(int j = 1; j < n; j++){
                if(!map[i][j] && !map[i][j-1] && !map[i][j+1]){
                    map[i][j] = true;
                    dfs(count+1, max);
                    map[i][j] = false;
                }
            }
        }
    }

    public static boolean check(){

        for(int i = 1; i <=n; i++){
            int pos = i;
            int level = 1;

            while(level <= h){
                if(map[level][pos]) pos++;
                else if(map[level][pos-1]) pos--;
                level++;
            }

            if(pos == i) continue;
            else return false;
        }

        return true;
    }

}
