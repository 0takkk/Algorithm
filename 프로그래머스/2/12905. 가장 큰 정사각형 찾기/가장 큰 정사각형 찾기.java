class Solution
{
     public static int solution(int [][]board) {
        int answer = 0;

        int n = board.length;
        int m = board[0].length;
        
        if(n < 2 || m < 2) {
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < m; j++) {
                    if(board[i][j] == 1) return 1;
                }
            }
            return 0;
        }

        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                if(board[i][j] != 0) {
                    int up = board[i - 1][j];
                    int left = board[i][j - 1];
                    int cross = board[i - 1][j - 1];
                    board[i][j] = Math.min(cross, Math.min(up, left)) + 1;
                    
                    answer = Math.max(answer, board[i][j] * board[i][j]);
                }
            }
        }

        return answer;
    }
}