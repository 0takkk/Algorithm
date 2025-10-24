class Solution {
    
    public int zero = 0;
    public int one = 0;
    public int[][] map;
    
    public int[] solution(int[][] arr) {
        int[] answer = new int[2];
        
        map = arr;
        int size = arr.length;
        
        rec(0, 0, size);
        
        answer[0] = zero;
        answer[1] = one;
        return answer;
    }
    
    public boolean isSame(int x, int y, int size) {
        int num = map[x][y];
        
        for(int i = x; i < x+size; i++) {
            for(int j = y; j < y+size; j++) {
                if(map[i][j] != num) return false;
            }
        }
        
        return true;
    }
    
    public void rec(int x, int y, int size) {
        if(isSame(x, y, size)) {
            if(map[x][y] == 0) {
                zero++;
            } else {
                one++;
            }
            return;
        }
        
        int nextSize = size/2;
        rec(x, y, nextSize);
        rec(x+nextSize, y, nextSize);
        rec(x, y+nextSize, nextSize);
        rec(x+nextSize, y+nextSize, nextSize);
    }
}