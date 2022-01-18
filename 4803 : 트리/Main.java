package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static boolean[] visited;
    public static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = 1;

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            int answer = 0;

            visited = new boolean[n+1];
            list = new ArrayList[n+1];
            for(int i = 1; i <= n; i++)
                list[i] = new ArrayList<>();

            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
                list[b].add(a);
            }

            for(int i = 1; i <= n; i++){
                if(!visited[i]){
                    if(dfs(i, 0)) answer++;
                }
            }
            
            if(answer > 1){
                sb.append("Case ").append(t).append(": A forest of ").append(answer).append(" trees.");
            }
            else if(answer == 1){
                sb.append("Case ").append(t).append(": There is one tree.");
            }
            else{
                sb.append("Case ").append(t).append(": No trees.");
            }
            sb.append("\n");
            t++;
        }

        System.out.println(sb.toString());
    }

    public static boolean dfs(int idx, int parent){
        visited[idx] = true;

        for(int next : list[idx]){
            if(next == parent) continue;
            else if(visited[next]) return false;
            else{
                if(!dfs(next, idx)) return false;
            }
        }

        return true;
    }

}
