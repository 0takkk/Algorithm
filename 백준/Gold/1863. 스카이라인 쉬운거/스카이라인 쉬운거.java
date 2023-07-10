import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] skyline = new int[n+1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            Integer.parseInt(st.nextToken());
            skyline[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i <= n; i++){
            while(!stack.isEmpty() && stack.peek() > skyline[i]){
                ans++;
                stack.pop();
            }

            if(stack.isEmpty() || stack.peek() != skyline[i]){
                stack.push(skyline[i]);
            }
        }

        System.out.println(ans);
    }

}
