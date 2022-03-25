package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(9, new int[][] {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}}));
    }

    public static ArrayList<Integer>[] list;

    public static int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];

            list[a].add(b);
            list[b].add(a);
        }

        for(int i = 0; i < wires.length; i++){
            int a = wires[i][0];
            int b = wires[i][1];

            int cnt1 = bfs(a, b, n);
            int cnt2 = bfs(b, a, n);

            answer = Math.min(answer, Math.abs(cnt1 - cnt2));
        }

        return answer;
    }

    public static int bfs(int a, int b, int n){
        boolean[] visited = new boolean[n+1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(a);
        visited[a] = true;

        int cnt = 0;

        while(!q.isEmpty()){
            int now = q.poll();
            cnt++;

            for(int next : list[now]){
                if(!visited[next] && next != b){
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }

        return cnt;
    }

}
