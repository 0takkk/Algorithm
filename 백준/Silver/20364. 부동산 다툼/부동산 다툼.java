import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        boolean[] grounds = new boolean[n+1];

        StringBuilder sb = new StringBuilder();
        while(q-->0) {
            int ground = Integer.parseInt(br.readLine());
            int tmp = ground;

            Stack<Integer> stack = new Stack<>();
            while(tmp > 1) {
                stack.push(tmp);
                tmp /= 2;
            }

            boolean flag = true;
            while(!stack.isEmpty()) {
                int route = stack.pop();
                if(grounds[route]) {
                    sb.append(route).append("\n");
                    flag = false;
                    break;
                }
            }

            if(flag) {
                grounds[ground] = true;
                sb.append("0\n");
            }
        }

        System.out.println(sb.toString());
    }

}
