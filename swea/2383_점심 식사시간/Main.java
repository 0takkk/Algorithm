import java.io.*;
import java.util.*;

public class Solution {

    public static class Person implements Comparable<Person>{
        int x, y;
        int stair;
        int dist;

        public Person(int x, int y, int stair) {
            this.x = x;
            this.y = y;
            this.stair = stair;
        }

        public void calDist() {
            this.dist = Math.abs(this.x - stairs[stair].x) + Math.abs(this.y - stairs[stair].y);
        }

        @Override
        public int compareTo(Person o) {
            return this.dist - o.dist;
        }

    }

    public static class Stair{
        int x, y;
        int time;

        public Stair(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static int n, ans;
    public static int[][] map;

    public static ArrayList<Person> people;
    public static Stair[] stairs;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int tc = 1; tc <= t; tc++) {
            ans = Integer.MAX_VALUE;
            n = Integer.parseInt(br.readLine());
            map = new int[n][n];

            people = new ArrayList<>();
            stairs = new Stair[2];

            for(int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 0) continue;
                    else if(map[i][j] == 1) people.add(new Person(i, j, 0));
                    else {
                        if(stairs[0] == null) stairs[0] = new Stair(i, j, map[i][j]);
                        else stairs[1] = new Stair(i, j, map[i][j]);
                    }
                }
            }

            dfs(0);

            sb.append("#").append(tc).append(" ").append(ans+1).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx) {
        if(idx == people.size()) {
            calTime();
            return;
        }

        for(int i = 0; i < 2; i++) {
            people.get(idx).stair = i;
            people.get(idx).calDist();
            dfs(idx+1);
        }
    }

    public static void calTime() {
        PriorityQueue<Person> pq1 = new PriorityQueue<>();
        PriorityQueue<Person> pq2 = new PriorityQueue<>();

        for(int i = 0; i < people.size(); i++) {
            Person person = people.get(i);

            if(person.stair == 0) pq1.offer(person);
            else pq2.offer(person);
        }

        Queue<Integer> ready1 = new ArrayDeque<>();
        Queue<Integer> ready2 = new ArrayDeque<>();

        int time = 0;
        while(!pq1.isEmpty() || !pq2.isEmpty()) {

            while(!pq1.isEmpty() && pq1.peek().dist <= time) {
                while(!ready1.isEmpty() && ready1.peek() <= time) {
                    ready1.poll();
                }

                if(ready1.size() < 3) {
                    ready1.offer((stairs[0].time + time));
                    pq1.poll();
                }else break;
            }


            while(!pq2.isEmpty() && pq2.peek().dist <= time) {
                while(!ready2.isEmpty() && ready2.peek() <= time) {
                    ready2.poll();
                }

                if(ready2.size() < 3) {
                    ready2.offer(stairs[1].time + time);
                    pq2.poll();
                }else break;
            }

            time++;

            if(time >= ans) return;
        }

        while(!ready1.isEmpty()) {
            time = Math.max(time, ready1.poll());
        }

        while(!ready2.isEmpty()) {
            time = Math.max(time, ready2.poll());
        }

        ans = Math.min(ans, time);
    }

}
