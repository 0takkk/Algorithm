import java.io.*;
import java.util.*;

public class Main {

    public static class Candidate implements Comparable<Candidate>{
        int num, count, idx;

        public Candidate(int num, int count, int idx) {
            this.num = num;
            this.count = count;
            this.idx = idx;
        }

        @Override
        public int compareTo(Candidate o) {
            if(this.count == o.count) {
                return o.idx - this.idx;
            }
            return this.count - o.count;
        }

        @Override
        public String toString() {
            return "Candidate{" +
                    "num=" + num +
                    ", count=" + count +
                    ", idx=" + idx +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] counts = new int[101];
        LinkedList<Candidate> rank = new LinkedList<>();

        while(k-->0) {
            int num = Integer.parseInt(st.nextToken());

            counts[num]++;
            if(rank.size() < n) {
                boolean flag = false;
                int idx = 0;
                for(int i = 0; i < rank.size(); i++) {
                    if(rank.get(i).num == num) {
                        flag = true;
                        idx = i;
                    }
                }

                if(flag) {
                    rank.get(idx).count++;
                }
                else {
                    rank.add(new Candidate(num, counts[num], k));
                }
            }
            else {
                boolean flag = false;
                int idx = 0;
                for(int i = 0; i < rank.size(); i++) {
                    if(rank.get(i).num == num) {
                        flag = true;
                        idx = i;
                    }
                }

                if(flag) {
                    rank.get(idx).count++;
                }
                else {

                        Candidate removed = rank.remove(0);
                        counts[removed.num] = 0;
                        rank.add(new Candidate(num, counts[num], k));
                    
                }
            }

            Collections.sort(rank);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (Candidate candidate : rank) {
            result.add(candidate.num);
        }

        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (Integer i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }

}