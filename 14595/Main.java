package com.company;
import java.io.*;
import java.util.*;

public class Main {
    public static int n;
    public static int[] room;
    public static PriorityQueue<Pair> pq = new PriorityQueue<>();
    public static int count;

    public static class Pair implements Comparable<Pair>{
        int x, y;

        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Pair o){
            if(this.x > o.x)
                return 1;
            else if(this.x < o.x)
                return -1;
            else{
                if(this.y > o.y)
                    return -1;
                else
                    return 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        room = new int[n+1];

        for(int i = 1; i < n+1; i++)
            room[i] = i;

        if(m == 0)
            System.out.println(n);
        else{
            while(m-->0){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                pq.add(new Pair(x, y));
            }

            solution();

            for(int i = 1; i <= n; i++){
                if(i == room[i]) count++;
            }
            System.out.println(count);
        }

    }

    public static void solution(){
        int x,y;
        int right = 0;

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            x = p.x;
            y = p.y;

            if(x < right) x = right;
            
            if(y > right){
                for(int i = x+1; i <= y; i++){
                    room[i] = room[x];
                }
                right = y;
            }
        }

    }


}
