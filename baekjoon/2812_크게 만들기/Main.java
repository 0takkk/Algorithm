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

        for (char c : arr) {
            while(k > 0 && !dq.isEmpty() && dq.peekLast() < c){
                dq.pollLast();
                k--;
            }

            dq.offerLast(c);
        }

        int size = dq.size()-k;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < size; i++){
            sb.append(dq.pollFirst());
        }

        System.out.println(sb.toString());
    }

}
