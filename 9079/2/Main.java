package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static boolean[] visited;
    public static boolean isFinish;
    public static int[] next;
    public static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        while(t-- > 0){
            visited = new boolean[512];
            isFinish = false;
            next = new int[8];
            count = 0;

            int start = 0;
            int idx = 0;

            for(int i = 0; i < 3; i++){
                st = new StringTokenizer(br.readLine(), " " );

                for(int j = 0; j < 3; j++) {
                    if (st.nextToken().equals("H"))
                        start += Math.pow(2, idx);
                    idx++;
                }
            }

            bfs(start);
            if(isFinish) System.out.println(count);
            else System.out.println(-1);
        }

    }

    public static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()){
            Queue<Integer> tmp = new LinkedList<>();
            while(!q.isEmpty()){
                tmp.offer(q.poll());
            }

            while(!tmp.isEmpty()){
                int now = tmp.poll();

                if(now == 0 || now == 511){
                    isFinish = true;
                    break;
                }

                findNext(now);
                for(int i = 0; i < 8; i++){
                    if(!visited[next[i]]){
                        visited[next[i]] = true;
                        q.offer(next[i]);
                    }
                }

            }

            if(isFinish) break;
            count++;
        }

    }

    public static void findNext(int start){
        next[0] = change(start, 0, 1, 2);
        next[1] = change(start, 3, 4, 5);
        next[2] = change(start, 6, 7, 8);
        next[3] = change(start, 0, 3, 6);
        next[4] = change(start, 1, 4, 7);
        next[5] = change(start, 2, 5, 8);
        next[6] = change(start, 0, 4, 8);
        next[7] = change(start, 2, 4, 6);
    }

    public static int change(int start, int n1, int n2, int n3){
        start = start ^ (1 << n1);
        start = start ^ (1 << n2);
        start = start ^ (1 << n3);
        return start;
    }


}
