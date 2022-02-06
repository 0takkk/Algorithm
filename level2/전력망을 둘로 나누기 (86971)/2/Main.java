package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(9, new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    public static int N, min, map[][];
    public static boolean[] visited;

    public static int solution(int n, int[][] wires) {
        N = n;
        min = n;

        map = new int[n+1][n+1];
        visited = new boolean[n+1];

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            map[a][b] = map[b][a] = 1;
        }

        dfs(1);

        return min;
    }

    public static int dfs(int n){
        visited[n] = true;
        int child = 1;

        for(int i = 1; i <= N; i++){
            if(!visited[i] && map[n][i] == 1){
                child += dfs(i);
            }
        }

        min = Math.min(min, Math.abs(child - (N - child)));
        return child;
    }

}
