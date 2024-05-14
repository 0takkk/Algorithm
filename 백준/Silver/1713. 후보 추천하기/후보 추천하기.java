import java.io.*;
import java.util.*;

public class Main {

    public static class Candidate implements Comparable<Candidate>{
        int num;
        int count;
        int idx;
        boolean isTopRank;

        public Candidate(int num, int count, int idx, boolean isTopRank) {
            this.num = num;
            this.count = count;
            this.idx = idx;
            this.isTopRank = isTopRank;
        }

        @Override
        public int compareTo(Candidate o) {
            if(this.count == o.count) {
                return o.idx - this.idx;
            }
            return this.count - o.count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Candidate[] candidates = new Candidate[101];
        List<Candidate> ranks = new ArrayList<>();

        for(int i = 1; i <= 100; i++) {
            candidates[i] = new Candidate(i, 0, 0, false);
        }

        while(m-->0) {
            int num = Integer.parseInt(st.nextToken());

            if(candidates[num].isTopRank) {
                candidates[num].count++;
            }
            else {
                if(ranks.size() == n) {
                    Collections.sort(ranks);
                    Candidate removed = ranks.remove(0);
                    candidates[removed.num].count = 0;
                    candidates[removed.num].isTopRank = false;
                }
                ranks.add(candidates[num]);
                candidates[num].count++;
                candidates[num].idx = m;
                candidates[num].isTopRank = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= 100; i++) {
            if(candidates[i].isTopRank) {
                sb.append(i).append(" ");
            }
        }

        System.out.println(sb.toString());
    }

}
