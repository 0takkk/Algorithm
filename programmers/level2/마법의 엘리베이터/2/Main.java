import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(535));
    }

    public static class Node{
        int num, count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static int solution(int storey) {
        return bfs(storey);
    }

    public static int bfs(int storey){
        int ans = Integer.MAX_VALUE;

        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(storey, 0));

        while(!q.isEmpty()){
            Node node = q.poll();
            int num = node.num;
            int count = node.count;

            if(ans < count) continue;

            int r = num % 10;

            if(num < 10){
                ans = Math.min(ans, count + num);
                ans = Math.min(ans, count + (10 - num) + 1);
                continue;
            }

            if(r == 0){
                q.offer(new Node(num/10, count));
                continue;
            }

            int num1 = num / 10;
            q.offer(new Node(num1, count + r));

            int num2 = num / 10 + 1;
            q.offer(new Node(num2, count + (10 - r)));
        }

        return ans;
    }

}
