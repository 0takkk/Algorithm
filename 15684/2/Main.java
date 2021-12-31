package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, h, result;
    public static int[][] map;
    public static boolean isPossible;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h+1][n+1];

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            map[a][b] = 1;
            map[a][b+1] = 2;
        }

        for(int i = 0; i <= 3; i++){
            result = i;
            dfs(0, 1);

            if(isPossible) break;
        }

        System.out.println(isPossible ? result : -1);
    }

    public static void dfs(int count, int x){
        if(isPossible) return;

        if(count == result){
            if(check()) isPossible = true;
            return;
        }

        for(int i = x; i <= h; i++){
            for(int j = 1; j < n; j++){
                if(map[i][j] == 0 && map[i][j+1] == 0){
                    map[i][j] = 1;
                    map[i][j+1] = 2;
                    dfs(count+1, i);
                    map[i][j] = map[i][j+1] = 0;
                }
            }
        }
    }

    public static boolean check() {
        for(int i = 1; i <= n; i++){
            int pos = i;
            int level = 1;

            while(level <= h){
                if(map[level][pos] == 1) pos++;
                else if(map[level][pos] == 2) pos--;
                level++;
            }

            if(pos == i) continue;
            else return false;
        }
        return true;
    }

}
