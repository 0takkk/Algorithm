import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(8, 2, new String[] {"D 2","C","U 3","C","D 4","C","U 2","Z","Z"}));
    }

    public static class Node{
        int pre, cur, next;

        public Node(int pre, int cur, int next) {
            this.pre = pre;
            this.cur = cur;
            this.next = next;
        }
    }

    public static String solution(int n, int k, String[] cmd) {
        String answer = "";

        int[] pre = new int[n];
        int[] next = new int[n];
        for(int i = 0; i < n; i++){
            pre[i] = i-1;
            next[i] = i+1;
        }
        next[n-1] = -1;

        Stack<Node> removed = new Stack<>();
        StringBuilder sb = new StringBuilder("O".repeat(n));

        for (String s : cmd) {
            char c = s.charAt(0);

            if(c == 'U') {
                int num = Integer.parseInt(s.substring(2));
                while(num-->0){
                    k = pre[k];
                }
            }
            else if(c == 'D') {
                int num = Integer.parseInt(s.substring(2));
                while(num-->0){
                    k = next[k];
                }
            }
            else if(c == 'C'){
                removed.push(new Node(pre[k], k, next[k]));
                if(pre[k] != -1) next[pre[k]] = next[k];
                if(next[k] != -1) pre[next[k]] = pre[k];
                sb.setCharAt(k, 'X');

                if(next[k] != -1) k = next[k];
                else k = pre[k];
            }
            else if(c == 'Z'){
                Node node = removed.pop();
                if(node.pre != -1) next[node.pre] = node.cur;
                if(node.next != -1) pre[node.next] = node.cur;
                sb.setCharAt(node.cur, 'O');
            }
        }

        answer = sb.toString();
        return answer;
    }

}
