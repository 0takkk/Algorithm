import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int height;
        int idx;

        public Node(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "height=" + height +
                    ", idx=" + idx +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Node> stack = new Stack<>();

        int[] ans = new int[n];

        for(int i = n-1; i >= 0; i--){
            int num = arr[i];

            while(!stack.isEmpty() && stack.peek().height < num){
                ans[stack.pop().idx] = i+1;
            }
            stack.push(new Node(num, i));
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            sb.append(ans[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

}
