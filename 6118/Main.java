package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static ArrayList<Integer>[] list;
    public static int maxIdx = -1, maxDist = -1 , maxCount = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 0; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        bfs(1);
        System.out.println(maxIdx + " " + maxDist + " " + maxCount);
    }

    public static void bfs(int cur){
        int[] dist = new int[n+1];
        boolean[] visited = new boolean[n+1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();

        dist[cur] = 0;
        visited[cur] = true;
        queue.offer(cur);

        while(!queue.isEmpty()){
            int idx = queue.poll();

            for(int i = 0; i < list[idx].size(); i++){
                int next = list[idx].get(i);

                if(!visited[next] && dist[next] > dist[idx] + 1){
                    dist[next] = dist[idx] + 1;
                    queue.offer(next);
                    visited[next] = true;
                }
            }
        }


        for(int i = 1; i <= n; i++){
            if(maxDist < dist[i]){
                maxDist = dist[i];
                maxIdx = i;
                maxCount = 1;
            }
            else if(maxDist == dist[i]) maxCount++;
        }
    }

}
