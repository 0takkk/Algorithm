import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(10, 40, 5));
    }

    public static class Node{
        int x, cnt;

        public Node(int x, int cnt) {
            this.x = x;
            this.cnt = cnt;
        }
    }

    public static final int SIZE = 1000000;

    public static int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }

    public static int bfs(int x, int y, int n){
        boolean[] visited = new boolean[SIZE+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, 0));
        visited[x] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == y) return now.cnt;

            for(int i = 0; i < 3; i++){
                int nx;
                if(i == 0) nx = now.x + n;
                else if(i == 1) nx = now.x * 2;
                else nx = now.x * 3;

                if(!isRange(nx, y)) continue;

                if(!visited[nx]){
                    visited[nx] = true;
                    q.offer(new Node(nx, now.cnt+1));
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int x, int y){
        return x <= y && x <= SIZE;
    }

}
