import java.util.*;

class Solution {
    
    public class Pos implements Comparable<Pos> {
        int x, y;
        int dir;
        int cost;
        
        public Pos(int x, int y, int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
        
        public int compareTo(Pos p) {
            return this.cost - p.cost;
        }
    }
    
    public int n = 0;
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    public int MAX = 987654321;
    
    public int solution(int[][] board) {
        int answer = MAX;
        
        n = board.length;
        // int[][] visited = new int[n][n];
        int[][][] visited = new int[n][n][4];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], MAX);
            }
        }
        
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        if(board[0][1] == 0) {
            pq.offer(new Pos(0, 1, 0, 100));
            // visited[0][1] = 100;
            visited[0][1][0] = 100;
        }
        if(board[1][0] == 0) {
            pq.offer(new Pos(1, 0, 2, 100));
            visited[1][0][2] = 100;
        }
        
        while(!pq.isEmpty()) {
            Pos p = pq.poll();
            int x = p.x;
            int y = p.y;
            int dir = p.dir;
            int cost = p.cost;
            
            if(x == n-1 && y == n-1) {
                answer = Math.min(answer, cost);
                continue;
            }

            for(int nextDir = 0; nextDir < 4; nextDir++) {
                int nx = x + dx[nextDir];
                int ny = y + dy[nextDir];
                
                if(isRange(nx, ny) && board[nx][ny] == 0) {
                    int nextCost = 0;
                    if(dir == 0 || dir == 1) {
                        if(nextDir == 0 || nextDir == 1) {
                            nextCost = cost+100;
                        } else {
                            nextCost = cost+600;
                        }
                    } else {
                        if(nextDir == 0 || nextDir == 1) {
                            nextCost = cost+600;
                        } else {
                            nextCost = cost+100;
                        }
                    }
                    
                    if(visited[nx][ny][dir] >= nextCost) {
                        visited[nx][ny][dir] = nextCost;
                        pq.offer(new Pos(nx, ny, nextDir, nextCost));
                    }
                    // if(visited[nx][ny] >= nextCost) {
                    //     visited[nx][ny] = nextCost;
                    //     pq.offer(new Pos(nx, ny, nextDir, nextCost));
                    // }
                }
            }
        }
        
        
        
        return answer;
    }
    
    public boolean isRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}