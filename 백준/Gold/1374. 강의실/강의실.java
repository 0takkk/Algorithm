import java.io.*;
import java.util.*;

public class Main {

    public static class Study implements Comparable<Study>{
        int num, start, end;

        public Study(int num, int start, int end) {
            this.num = num;
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Study o) {
            if(this.end == o.end) return this.start - o.start;
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        ArrayList<Study> studies = new ArrayList<>();

        while(n-->0){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            studies.add(new Study(num, start, end));
        }

        studies.sort(Comparator.comparingInt(s -> s.start));

        PriorityQueue<Study> pq = new PriorityQueue<>();

        int ans = 0;

        for (Study study : studies) {
            int num = study.num;
            int start = study.start;
            int end = study.end;

            while(!pq.isEmpty() && pq.peek().end <= start){
                pq.poll();
            }

            pq.offer(new Study(num, start, end));
            ans = Math.max(ans, pq.size());
        }

        System.out.println(ans);
    }

}
