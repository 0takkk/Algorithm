package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int A, B;
    public static boolean[] visited;
    public static String[] command;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            st = new StringTokenizer(br.readLine());

            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            visited = new boolean[10000];
            command = new String[10000];
            Arrays.fill(command, "");

            bfs();
            System.out.println(command[B]);
        }
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();

        visited[A] = true;
        q.offer(A);

        while(!q.isEmpty() && !visited[B]){
            int now = q.poll();

            int D = (now * 2) % 10000;
            int S = now == 0 ? 9999 : now-1;
            int L = (now % 1000) * 10 + (now / 1000);
            int R = (now % 10) * 1000 + (now / 10);

            if(!visited[D]){
                q.offer(D);
                visited[D] = true;
                command[D] = command[now] + "D";
            }
            if(!visited[S]){
                q.offer(S);
                visited[S] = true;
                command[S] = command[now] + "S";
            }
            if(!visited[L]){
                q.offer(L);
                visited[L] = true;
                command[L] = command[now] + "L";
            }
            if(!visited[R]){
                q.offer(R);
                visited[R] = true;
                command[R] = command[now] + "R";
            }
        }
    }
}
