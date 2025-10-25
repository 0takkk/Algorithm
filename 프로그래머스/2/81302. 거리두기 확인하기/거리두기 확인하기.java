class Solution {
    
    public char[][] map;
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        int idx = 0;
        for(String[] place : places) {
            map = new char[5][5];
            for(int i = 0; i < 5; i++) {
                map[i] = place[i].toCharArray();
            }

            boolean clear = true;
            for(int i = 0; i < 5; i++) {
                for(int j = 0; j < 5; j++) {
                    if(map[i][j] == 'P') {
                        if(!isClear(i, j)) {
                            clear = false;
                        } 
                    }
                }
            }
            
            answer[idx++] = clear ? 1 : 0;
        }
        
        return answer;
    }
    
    public int[] dx = {0, 0, 1, -1};
    public int[] dy = {1, -1, 0, 0};
    
    public int[] cx = {-1, -1, 1, 1};
    public int[] cy = {-1, 1, -1, 1};
    
    public boolean isClear(int x, int y) {
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if(isRange(nx, ny) && map[nx][ny] == 'P') {
                return false;
            }
            
            nx = x + 2 * dx[i];
            ny = y + 2 * dy[i];
            if(isRange(nx, ny) && map[nx][ny] == 'P') {
                if(map[x+dx[i]][y+dy[i]] != 'X') {
                    return false;
                }
            }
        }
        
        for(int i = 0; i < 4; i++) {
            int nx = x + cx[i];
            int ny = y + cy[i];
            
            if(isRange(nx, ny) && map[nx][ny] == 'P') {
                if(map[x + cx[i]][y] != 'X' || map[x][y + cy[i]] != 'X') {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    public boolean isRange(int x, int y) {
        return 0 <= x && x < 5 && 0 <= y && y < 5;
    }
}