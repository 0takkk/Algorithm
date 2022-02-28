package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static int n, result = Integer.MAX_VALUE;
    public static int[][] map;
    public static int[] dx = {0, 1};
    public static int[] dy = {1, 0};

    public static class Node implements Comparable<Node>{
        int x, y, weight;

        public Node(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dijkstra();

        System.out.println(result);
    }

    public static void dijkstra(){
        boolean[][] visited = new boolean[n+1][n+1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(1,1, 0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            int x = now.x;
            int y = now.y;
            int weight = now.weight;

            if(x == n && y == n){
                result = Math.min(result, weight);
            }

            if(visited[x][y]) continue;
            visited[x][y] = true;

            for(int i = 0; i < 2; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 1 || nx > n || ny < 1 || ny > n) continue;

                int diff = 0;
                if(map[nx][ny] >= map[x][y]) diff = map[nx][ny] - map[x][y] + 1;

                pq.offer(new Node(nx, ny, weight + diff));
            }
        }
    }

}
