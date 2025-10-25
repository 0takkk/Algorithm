import java.util.*;

class Solution {
    
    public class Pos {
        int x, y;
        int move;
        
        public Pos(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
    
    public int[][] map;
    public boolean[] card;
    public int cardCount;
    public List<String> orders;
    
    public Pos cursor;
    
    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;
        
        card = new boolean[7];
        cardCount = 0;
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if(board[i][j] != 0) {
                    card[board[i][j]] = true;
                    cardCount++;
                }
            }
        }
        
        cardCount /= 2;
        orders = new ArrayList<>();
        makeOrders("", 0);
        
        for(String order : orders) {
            cursor = new Pos(r, c, 0);
            map = new int[4][4];
            
            for(int i = 0; i < 4; i++) {
                for(int j = 0; j < 4; j++) {
                    map[i][j] = board[i][j];
                }
            }
            
            for(int i = 0; i < order.length(); i++) {
                int target = order.charAt(i) - '0';
                
                search(cursor, target);
                map[cursor.x][cursor.y] = 0;
                
                search(cursor, target);
                map[cursor.x][cursor.y] = 0;
            }
            
            answer = Math.min(answer, cursor.move);
        }        
        
        return answer;
    }
    
    public void makeOrders(String str, int cnt) {
        if(cnt == cardCount) {
            orders.add(str);
            return;
        }
        
        for(int i = 1; i <= 6; i++) {
            if(card[i] && !str.contains(i+"")) {
                makeOrders(str+i, cnt+1);
            }
        }
    }
    
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    
    public void search(Pos pos, int target) {
        boolean[][] visited = new boolean[4][4];
        visited[pos.x][pos.y] = true;
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        
        while(!q.isEmpty()) {
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            int move = p.move;
            
            if(map[x][y] == target) {
                cursor = new Pos(x, y, move+1);
                return;
            }
            
            for(int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                
                if(isRange(nx, ny) && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, move+1));
                }
            }
            
            for(int dir = 0; dir < 4; dir++) {
                Pos ctrlPos = ctrlMove(p, dir);
                int nx = ctrlPos.x;
                int ny = ctrlPos.y;
                int nmove = ctrlPos.move;
                
                if(nx == x && ny == y) {
                    continue;
                }
                
                if(!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny, nmove));
                }
            }
        }
    }
    
    public Pos ctrlMove(Pos pos, int dir) {
        int x = pos.x;
        int y = pos.y;
        int move = pos.move;
        
        int nx = x + dx[dir];
        int ny = y + dy[dir];
        
        while(isRange(nx, ny)) {
            if(map[nx][ny] != 0) {
                return new Pos(nx, ny, move+1);
            }
            
            nx += dx[dir];
            ny += dy[dir];
        }
        
        return new Pos(nx-dx[dir], ny-dy[dir], move+1);
    }
    
    public boolean isRange(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}