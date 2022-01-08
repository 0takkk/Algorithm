package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
    }

    public static ArrayList<Integer>[] list;
    public static boolean[] visited;

    public static int solution(int n, int[][] computers) {
        int answer = 0;

        list = new ArrayList[n];
        for(int i = 0; i < n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(i != j && computers[i][j] == 1){
                    list[i].add(j);
                }
            }
        }

        visited = new boolean[n];

        for(int i = 0; i < n; i++){
            if(!visited[i]) {
                answer++;
                dfs(i);
            }

        }

        return answer;
    }

    public static void dfs(int idx){
        if(!visited[idx]){
            visited[idx] = true;

            for(int next : list[idx]){
                dfs(next);
            }
        }
    }

}
