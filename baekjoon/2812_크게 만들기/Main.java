import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        char[] arr = br.readLine().toCharArray();

        Deque<Character> dq = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            char num = arr[i];

            while(k > 0 && !dq.isEmpty() && dq.peekLast() < num){
                dq.pollLast();
                k--;
            }

            dq.offerLast(num);
        }

        StringBuilder sb = new StringBuilder();
        while(dq.size() > k){
            sb.append(dq.pollFirst());
        }

        System.out.println(sb.toString());
    }

}
