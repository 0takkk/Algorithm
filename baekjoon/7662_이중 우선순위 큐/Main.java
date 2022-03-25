package com.company;
import java.io.*;
import java.util.*;

public class Main {

    public static PriorityQueue<Integer> minQ, maxQ;
    public static HashMap<Integer, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            int k = Integer.parseInt(br.readLine());

            minQ = new PriorityQueue<>();
            maxQ = new PriorityQueue<>(Comparator.reverseOrder());
            map = new HashMap<>();

            while(k-->0){
                st = new StringTokenizer(br.readLine());

                char ch = st.nextToken().charAt(0);
                int n = Integer.parseInt(st.nextToken());

                operation(ch, n);
            }

            if(map.size() == 0) sb.append("EMPTY");
            else{
                int max = removeMap(maxQ);
                int min = map.size() > 0 ? removeMap(minQ) : max;

                sb.append(max + " " + min);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void operation(char ch, int n){
        if(ch == 'I'){
            map.put(n, map.getOrDefault(n, 0) + 1);

            minQ.offer(n);
            maxQ.offer(n);
        }
        else{
            if(map.size() == 0) return;

            PriorityQueue<Integer> pq = n == 1 ? maxQ : minQ;
            removeMap(pq);
        }
    }

    private static int removeMap(PriorityQueue<Integer> pq) {
        int num;
        while(true){
            num = pq.poll();

            int cnt = map.getOrDefault(num, 0);

            if(cnt == 0) continue;

            if(cnt == 1) map.remove(num);
            else map.put(num, cnt-1);

            break;
        }

        return num;
    }

}
