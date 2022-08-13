import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            int n = Integer.parseInt(br.readLine());

            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++){
                pq.offer(Long.parseLong(st.nextToken()));
            }

            long ans = 0;
            while(pq.size() >= 2){
                long sum = pq.poll() + pq.poll();
                ans += sum;
                pq.offer(sum);
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb.toString());
    }

}
