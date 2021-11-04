package com.company;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++)
            list[i] = new ArrayList<>();

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine(), " ");
            int f = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());

            list[f].add(s);
            list[s].add(f);
        }

        int[] parents = new int[n+1];

        dfs(list, parents, n, 1, 0);

        for(int i = 2; i <= n; i++)
            System.out.println(parents[i]);
    }

    public static void dfs(ArrayList<Integer>[] list, int[] parents, int n, int start, int parent){
        parents[start] = parent;

        for(int item : list[start]){
            if(item != parent)
                dfs(list, parents, n, item, start);
        }
    }
}
