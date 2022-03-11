package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, arr[];
    public static boolean[] visited;
    public static int[] dir = {1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        arr = new int[n];
        for(int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        int s = Integer.parseInt(br.readLine());

        System.out.println(dfs(s-1));
    }

    public static int dfs(int idx){
        Queue<Integer> q = new LinkedList<>();
        q.offer(idx);
        visited[idx] = true;
        int count = 1;

        while(!q.isEmpty()){
            int now = q.poll();

            for(int i = 0; i < 2; i++){
                int next = now + dir[i] * arr[now];

                if(next < 0 || next >= n) continue;

                if(!visited[next]){
                    visited[next] = true;
                    q.offer(next);
                    count++;
                }
            }
        }

        return count;
    }

}
