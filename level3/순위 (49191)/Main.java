package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(5, new int[][] {{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    public static boolean[] visited;

    public static int solution(int n, int[][] results) {
        int answer = 0;

        ArrayList<Integer>[] win = new ArrayList[n+1];
        ArrayList<Integer>[] lose = new ArrayList[n+1];
        for(int i = 0; i <= n; i++){
            win[i] = new ArrayList<>();
            lose[i] = new ArrayList<>();
        }

        for(int i = 0; i < results.length; i++){
            int winner = results[i][0];
            int looser = results[i][1];

            win[winner].add(looser);
            lose[looser].add(winner);
        }

        for(int i = 1; i <= n; i++){
            int winCnt = 0;
            int loseCnt = 0;

            visited = new boolean[n+1];
            winCnt = check(i, lose, winCnt);

            visited = new boolean[n+1];
            loseCnt = check(i, win, loseCnt);

            if(winCnt + loseCnt == n-1) answer++;
        }

        return answer;
    }

    public static int check(int idx, ArrayList<Integer>[] graph, int count){
        visited[idx] = true;

        for(int next : graph[idx]){
            if(!visited[next]){
                count = check(next, graph, count+1);
            }
        }

        return count;
    }

}
