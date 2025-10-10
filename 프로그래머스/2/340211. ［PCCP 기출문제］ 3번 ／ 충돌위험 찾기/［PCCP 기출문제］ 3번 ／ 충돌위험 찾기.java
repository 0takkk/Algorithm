import java.util.*;

class Solution {

    public class Robot {
        int idx;
        int x, y;
        int target;
        int targetIdx;
        
        public Robot(int idx, int x, int y, int target, int targetIdx) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.target = target;
            this.targetIdx = targetIdx;
        }
    }
    
    public int[][] visited;
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        
        visited = new int[101][101];
        Queue<Robot> q = new LinkedList<>();
        
        for(int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            
            int start = route[0];
            int next = route[1];
            
            int x = points[start-1][0];
            int y = points[start-1][1];
            
            if(visited[x][y]++ == 1) {
                answer++;
            }
            
            q.offer(new Robot(i, x, y, next, 1));
        }
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            visited = new int[101][101];
            
            while(size-->0) {
                Robot r = q.poll();
                int idx = r.idx;
                int x = r.x;
                int y = r.y;
                int target = r.target;
                int targetIdx = r.targetIdx;
                
                int[] nextMove = getNextMove(x, y, target, points);
                int nx = nextMove[0];
                int ny = nextMove[1];
                
                if(visited[nx][ny]++ == 1) {
                    answer++;
                }
                
                if(isArrive(nx, ny, target, points)) {
                    if(targetIdx < routes[0].length-1) {
                        q.offer(new Robot(idx, nx, ny, routes[idx][targetIdx+1], targetIdx+1));
                    }
                } else {
                    q.offer(new Robot(idx, nx, ny, target, targetIdx));
                }
            }
        }

        return answer;
    }
    
    public int[] getNextMove(int x, int y, int target, int[][] points) {
        int targetX = points[target-1][0];
        int targetY = points[target-1][1];
        
        if(x == targetX) {
            int ny = targetY > y ? y+1 : y-1;
            return new int[]{x, ny};
        } else {
            int nx = targetX > x ? x+1 : x-1;
            return new int[]{nx, y};
        }
    }
    
    public boolean isArrive(int x, int y, int target, int[][] points) {
        int targetX = points[target-1][0];
        int targetY = points[target-1][1];
        
        return x == targetX && y == targetY;
    }
}