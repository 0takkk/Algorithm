import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new int[][] {{4,5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}}));
    }

    public static class Node implements Comparable<Node>{
        int s, e;

        public Node(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Node o) {
            return this.e - o.e;
        }
    }

    public static int solution(int[][] targets) {
        int answer = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int[] target : targets) {
            pq.offer(new Node(target[0], target[1]));
        }

        int end = pq.peek().e;
        while(!pq.isEmpty()){
            while(!pq.isEmpty() && pq.peek().s < end){
                pq.poll();
            }

            answer++;
            if(!pq.isEmpty()) end = pq.peek().e;
        }

        return answer;
    }

}
