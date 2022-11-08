import java.io.*;
import java.util.*;

public class Main {

    public static class Person implements Comparable<Person>{
        int start, end;

        public Person(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Person o) {
            if(this.start == o.start) return this.end - o.end;
            return this.start - o.start;
        }
    }

    public static int n, t, p;
    public static PriorityQueue<Person> pq;
    public static boolean[][] usingSeat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        p = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();

        for(int i = 1; i <= t; i++){
            st = new StringTokenizer(br.readLine());
            pq.add(new Person(timeToMinute(st.nextToken()), timeToMinute(st.nextToken())));
        }

        usingSeat = new boolean[1261][n+1];

        while(!pq.isEmpty()){
            Person person = pq.poll();
            int start = person.start;
            int end = person.end;
            int seat = findSeat(start);

            for(int i = start; i < end; i++){
                usingSeat[i][seat] = true;
            }
        }

        int ans = 0;
        for(int i = 540; i < 1260; i++){
            if(!usingSeat[i][p]) ans++;
        }

        System.out.println(ans);
    }


    public static int findSeat(int time){
        boolean isEmpty = true;
        for(int i = 1; i <= n; i++){
            if(usingSeat[time][i]){
               isEmpty = false;
               break;
            }
        }

        if(isEmpty) return 1;

        int idx = 0;
        int result = 0;

        for(int i = 1; i <= n; i++){
            if(usingSeat[time][i]) continue;

            int min = Integer.MAX_VALUE;
            for(int j = 1; j <= n; j++){
                if(i == j || !usingSeat[time][j]) continue;

                min = Math.min(min, Math.abs(i-j));
            }

            if(min > result){
                result = min;
                idx = i;
            }
        }

        return idx;
    }

    public static int timeToMinute(String time){
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(2));
    }

}
