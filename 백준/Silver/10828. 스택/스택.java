import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        while(n-->0) {
            st = new StringTokenizer(br.readLine());
            String comd = st.nextToken();

            switch (comd) {
                case "push":
                    int num = Integer.parseInt(st.nextToken());
                    stack.push(num);
                    break;
                case "pop":
                    if(stack.empty()) sb.append("-1");
                    else sb.append(stack.pop());
                    sb.append("\n");
                    break;
                case "size":
                    sb.append(stack.size()).append("\n");
                    break;
                case "empty":
                    if(stack.empty()) sb.append(1);
                    else sb.append(0);
                    sb.append("\n");
                    break;
                default:
                    if(stack.empty()) sb.append("-1");
                    else sb.append(stack.peek());
                    sb.append("\n");
                    break;
            }
        }

        System.out.println(sb.toString());
    }

}
