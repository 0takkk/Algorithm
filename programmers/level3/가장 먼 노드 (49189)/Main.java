package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
	    System.out.println(solution(6, new int[][] {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    public static boolean[] visited;
    public static ArrayList<Integer>[] list;
    public static int[] distance;

    public static int solution(int n, int[][] edge) {
        int answer = 0;

        visited = new boolean[n+1];
        distance = new int[n+1];
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < edge.length; i++){
            int a = edge[i][0];
            int b = edge[i][1];

            list[a].add(b);
            list[b].add(a);
        }

        bfs();

        int max = 0;
        for(int i = 1; i <= n; i++){
            max = Math.max(max, distance[i]);
        }

        for(int i = 1; i <=n; i++){
            if(max == distance[i])
                answer++;
        }


        return answer;
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        visited[1] = true;
        distance[1] = 0;

        while(!q.isEmpty()){
            int now = q.poll();

            for(int next : list[now]){
                if(!visited[next]){
                    distance[next] = distance[now] + 1;
                    visited[next] = true;
                    q.offer(next);
                }
            }
        }

    }

}
