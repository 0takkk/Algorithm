package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, degree[], result[];
    public static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        degree = new int[n+1];
        result = new int[n+1];
        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) list[i] = new ArrayList<>();

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            degree[b]++;
        }

        topologySort();

        for(int i = 1; i <= n; i++){
            System.out.print(result[i] + " ");
        }

    }

    public static void topologySort(){

        Queue<Integer> q = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            if(degree[i] == 0) q.offer(i);
        }

        for(int i = 1; i <= n; i++){
            int now = q.poll();

            result[i] = now;

            for(int next : list[now]){
                if(--degree[next] == 0){
                    q.offer(next);
                }
            }
        }
    }

}
