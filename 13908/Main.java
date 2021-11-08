package com.company;
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int n, m, count;
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[10];

        if(m != 0) {
            st = new StringTokenizer(br.readLine(), " ");

            for (int i = 0; i < m; i++)
                visited[Integer.parseInt(st.nextToken())] = true;
        }

        backtracking(0,0);

        System.out.println(count);
    }

    public static void backtracking(int idx, int cnt){
        if(idx == n){
            if(cnt == m ) count++;
            return;
        }

        for(int i = 0; i <= 9; i++){
            if(visited[i]){
                visited[i] = false;
                backtracking(idx + 1, cnt + 1);
                visited[i] = true;
            }else{
                backtracking(idx + 1, cnt);
            }
        }
    }
}
