package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(80, new int[][] {{80,20},{50,40},{30,10}}));
    }

    public static int max = 0;
    public static boolean[] visited;

    public static int solution(int k, int[][] dungeons) {
        int answer = -1;

        visited = new boolean[dungeons.length];

        dfs(0, k, dungeons);

        answer = max;
        return answer;
    }

    public static void dfs(int cnt, int k, int[][] dungeons){
        for(int i = 0; i < dungeons.length; i++){
            if(!visited[i] && k >= dungeons[i][0]){
                visited[i] = true;
                dfs(cnt+1, k-dungeons[i][1], dungeons);
                visited[i] = false;
            }
        }

        max = Math.max(max, cnt);
    }

}
