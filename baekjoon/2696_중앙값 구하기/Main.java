import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while(t-->0){
            int m = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
            PriorityQueue<Integer> min = new PriorityQueue<>();

            sb.append((m+1)/2).append("\n");
            int cnt = 0;

            for(int i = 0; i < m; i++){
                if(i % 10 == 0) st = new StringTokenizer(br.readLine());

                int num = Integer.parseInt(st.nextToken());

                if(max.size() == min.size()) max.offer(num);
                else min.offer(num);

                if(!min.isEmpty()){
                    if(max.peek() > min.peek()){
                        int n1 = max.poll();
                        int n2 = min.poll();

                        max.offer(n2);
                        min.offer(n1);
                    }
                }

                if(i % 2 == 0){
                    sb.append(max.peek());

                    if(cnt == 9 || i == m-1){
                        sb.append("\n");
                        cnt = 0;
                    }else sb.append(" ");

                    cnt++;
                }
            }
        }

        System.out.println(sb.toString());
    }
}
