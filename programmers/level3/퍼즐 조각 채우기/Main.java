import java.util.*;

class Solution {
    
    public static class Pos implements Comparable<Pos>{
        int x, y;
        
        public Pos(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int compareTo(Pos p){
            if(this.x == p.x){
                return this.y - p.y;
            }
            return this.x - p.x;
        }
        
        public String toString(){
            return "(x = " + x + ", y = " + y + ")"; 
        }
    }
    
    public static int n;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};
    
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = -1;
        
        n = table.length;
        ArrayList<ArrayList<Pos>> tableList = new ArrayList<>();
        ArrayList<ArrayList<Pos>> boardList = new ArrayList<>();
        
        boolean[][] visitedTable = new boolean[n][n];
        boolean[][] visitedBoard = new boolean[n][n];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(table[i][j] == 1 && !visitedTable[i][j]){
                    bfs(new Pos(i, j), table, 1, visitedTable, tableList);
                }
                
                if(game_board[i][j] == 0 && !visitedBoard[i][j]){
                    bfs(new Pos(i, j), game_board, 0, visitedBoard, boardList);
                }
            }
        }
        
        return check(tableList, boardList);
    }
        
    public static int check(ArrayList<ArrayList<Pos>> tableList, ArrayList<ArrayList<Pos>> boardList){
        int size = boardList.size();
        boolean[] visitedBoard = new boolean[size];
        int ans = 0;
        
        for(ArrayList<Pos> table : tableList){
            for(int i = 0; i < boardList.size(); i++){
                ArrayList<Pos> board = boardList.get(i);
                
                if(visitedBoard[i]) continue;
                if(table.size() != board.size()) continue;
                
                if(rotate(table, board)){
                    visitedBoard[i] = true;
                    ans += board.size();
                    break;
                }
            }
        }
        
        return ans;
    }
    
    public static boolean rotate(ArrayList<Pos> table, ArrayList<Pos> board){
        boolean isMatch = true;
        
        Collections.sort(board);
        
        for(int i = 0; i < 4; i++){
            Collections.sort(table);
            
            int x = table.get(0).x;
            int y = table.get(0).y;
            
            for(Pos p : table){
                p.x -= x;
                p.y -= y;
            }
            
            isMatch = true;
            for(int j = 0; j < table.size(); j++){
                if(table.get(j).x != board.get(j).x || table.get(j).y != board.get(j).y){
                    isMatch = false;
                    break;
                }
            }
            
            if(isMatch){
                return true;
            }else{  // 90도 회전 x, y -> y, -x
                for(Pos p : table){
                    int tmp = p.x;
                    p.x = p.y;
                    p.y = -tmp;
                }
                
            }
        }
        
        return false;
    }
    
    
    public static void bfs(Pos pos, int[][] map, int num, boolean[][] visited, ArrayList<ArrayList<Pos>>  list){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;
        
        ArrayList<Pos> puzzle = new ArrayList<>();
        puzzle.add(new Pos(0, 0));
        
        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;
            
            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                
                if(!isRange(nx, ny)) continue;
                
                if(!visited[nx][ny] && map[nx][ny] == num){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                    puzzle.add(new Pos(nx - pos.x, ny - pos.y));
                }
            }
        }
        
        list.add(puzzle);
    }
    
    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
