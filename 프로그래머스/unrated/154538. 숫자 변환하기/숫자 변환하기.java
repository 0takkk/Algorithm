import java.util.*;

class Solution {
    
    public static class Node{
        int num, count;
        
        public Node(int n, int c){
            num = n;
            count = c;
        }
    }
    
    public static int solution(int x, int y, int n) {
        return bfs(x, y, n);
    }
    
    public static int bfs(int x, int y, int n){
        boolean[] visited = new boolean[y+1];
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(x, 0));
        
        while(!q.isEmpty()){
            Node node = q.poll();
            int num = node.num;
            int count = node.count;
            
            if(num == y) return count;
            
            if(num + n <= y && !visited[num+n]){
                visited[num+n] = true;
                q.offer(new Node(num+n, count+1));
            }
            if(num * 2 <= y && !visited[num*2]){
                visited[num*2] = true;
                q.offer(new Node(num*2, count+1));
            }
            if(num * 3 <= y && !visited[num*3]){
                visited[num*3] = true;
                q.offer(new Node(num*3, count+1));
            }
        }
        
        return -1;
    }
    
}