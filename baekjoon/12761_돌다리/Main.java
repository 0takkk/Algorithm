package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int A, B, n, m;
    public static boolean[] visited;
    public static int[] dx;
    public static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[100001];
        dx = new int[] {1, -1, A, B, -A, -B, A, B};

        bfs();
        System.out.println(time);
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);

        while(!q.isEmpty()){

            int size = q.size();
            for(int i = 0; i < size; i++){
                int now = q.poll();

                if(now == m) return;

                for(int j = 0; j < 8; j++){
                    int idx;
                    if(j < 6) idx = now + dx[j];
                    else idx = now * dx[j];

                    if(idx < 0 || idx > 100000) continue;

                    if(!visited[idx]) {
                        q.offer(idx);
                        visited[idx] = true;
                    }
                }
            }

            time++;
        }
    }

}
