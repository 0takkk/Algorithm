package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static ArrayList<Integer>[] list;
    public static int[] depth;
    public static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            n = Integer.parseInt(br.readLine());

            list = new ArrayList[n+1];
            parent = new int[n+1];
            depth = new int[n+1];

            for(int i = 0; i <= n; i++)
                list[i] = new ArrayList<>();

            int[] cnt = new int[n+1];

            for(int i = 0; i < n-1; i++){
                st = new StringTokenizer(br.readLine(), " ");

                int parent = Integer.parseInt(st.nextToken());
                int child = Integer.parseInt(st.nextToken());

                list[parent].add(child);
                cnt[child]++;
            }

            int root = 0;
            for(int i = 1; i <= n; i++){
                if(cnt[i] == 0){
                    root = i;
                    break;
                }
            }

            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());

            makeTree(root);

            System.out.println(lca(num1, num2));
        }
    }

    public static int lca(int a, int b){
        while(true){
            if(a == b){
                return a;
            }

            if(depth[a] == depth[b]){
                while(a != b){
                    a = parent[a];
                    b = parent[b];
                }
            }
            else if(depth[a] < depth[b]){
                while(depth[a] < depth[b])
                    b = parent[b];

            }
            else{
                while(depth[a] > depth[b])
                    a = parent[a];
            }
        }
    }

    public static void makeTree(int root){
        Queue<Integer> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int tmp = q.poll();

            for(int child : list[tmp]){
                if(parent[child] != 0) continue;

                parent[child] = tmp;
                depth[child] = depth[tmp] + 1;
                q.offer(child);
            }
        }
    }

}
