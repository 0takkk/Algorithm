package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, t;
    public static ArrayList<Node>[] list;
    public static boolean[] visited;
    public static int count, times, root;

    public static class Node{
        int x, time;

        public Node(int x, int time) {
            this.x = x;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) list[i] = new ArrayList<>();

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }

        count = 1;
        root = 0;
        times = 0;

        visited = new boolean[n+1];
        dfs(new Node(1, 0), 1);

        visited = new boolean[n+1];
        dfs(new Node(root, 0), 1);

        times = (int)Math.ceil(times / (double)t);

        System.out.println(times);
    }

    public static void dfs(Node node, int cnt){

        if(count < cnt){
            count = cnt;
            root = node.x;
            times = node.time;
        }
        else if(count == cnt && times > node.time){
            root = node.x;
            times = node.time;
        }

        visited[node.x] = true;

        for(Node next : list[node.x]){
            if(!visited[next.x]){
                dfs(new Node(next.x, node.time + next.time), cnt+1);
            }
        }

    }

}
