package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static final int SIZE = 52, START = 0, END = 25;
    public static int n, capacity[][], flow[][], path[], result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        capacity = new int[SIZE][SIZE];
        flow = new int[SIZE][SIZE];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());

            int a = charToInt(st.nextToken().charAt(0));
            int b = charToInt(st.nextToken().charAt(0));
            int c = Integer.parseInt(st.nextToken());

            capacity[a][b] += c;
            capacity[b][a] += c;
        }

        bfs();

        System.out.println(result);
    }

    public static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        path = new int[SIZE];

        while(true){
            q.clear();
            Arrays.fill(path, -1);
            path[START] = START;  // A
            q.offer(START);

            while(!q.isEmpty()){
                int now = q.poll();

                for(int next = 0; next < SIZE; next++){
                    if(capacity[now][next] - flow[now][next] > 0 && path[next] == -1){
                        q.offer(next);
                        path[next] = now;
                        if(next == END) break; // Z
                    }
                }
            }

            if(path[END] == -1) break;  // 경로가 없으면 종료

            int flowAmount = Integer.MAX_VALUE;
            for(int i = END; i != START; i = path[i]){
                flowAmount = Math.min(flowAmount, capacity[path[i]][i] - flow[path[i]][i]);
            }

            for(int i = END; i != START; i = path[i]){
                flow[path[i]][i] += flowAmount;  // 경로들에 유량을 흘러줌
                flow[i][path[i]] -= flowAmount;  // 반대 경로에 -유량 흘림
            }

            result += flowAmount;
        }
    }

    public static int charToInt(char c){
        if('a' <= c && c <= 'z') c -= 6;
        return c-65;  // A = 0, B = 1, ... Z = 25, a = 26, ... z = 51;
    }
}
