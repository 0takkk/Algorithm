import java.io.*;
import java.util.*;

public class Main {

    public static class Bug implements Comparable<Bug>{
        int start, end;

        public Bug(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Bug o) {
            if(this.start == o.start){
                return this.end - o.end;
            }
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Bug[] bugs = new Bug[n];

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            bugs[i] = new Bug(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(bugs);

        int max = 0;
        int maxStart = 0, maxEnd = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            int start = bugs[i].start;
            int end = bugs[i].end;

            pq.offer(end);

            int prev = -1;
            while(start >= pq.peek()){
                prev = pq.poll();
            }

            if(pq.size() > max){
                maxStart = start;
                maxEnd = pq.peek();
                max = pq.size();
            }
            else if(pq.size() == max && prev == start){
                maxEnd = pq.peek();
            }
        }

        System.out.println(max);
        System.out.println(maxStart + " " + maxEnd);
    }

}
