public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"O.X", ".O.", "..X"}));
        System.out.println(solution(new String[] {"OOO", "...", "XXX"}));
        System.out.println(solution(new String[] {"...", ".X.", "..."}));
        System.out.println(solution(new String[] {"...", "...", "..."}));
    }

    public static char[][] map;
    public static int[][][] bingo = {
            {{0, 0}, {0, 1}, {0, 2}},
            {{1, 0}, {1, 1}, {1, 2}},
            {{2, 0}, {2, 1}, {2, 2}},
            {{0, 0}, {1, 0}, {2, 0}},
            {{0, 1}, {1, 1}, {2, 1}},
            {{0, 2}, {1, 2}, {2, 2}},
            {{0, 0}, {1, 1}, {2, 2}},
            {{0, 2}, {1, 1}, {2, 0}}
    };

    public static int solution(String[] board) {
        map = new char[3][3];
        int oCnt = 0;
        int xCnt = 0;

        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'O') oCnt++;
                else if(map[i][j] == 'X') xCnt++;
            }
        }

        if(xCnt > oCnt) return 0;
        if(oCnt >= xCnt+2) return 0;

        boolean oBingo = checkBingo('O');
        boolean xBingo = checkBingo('X');

        if(oBingo && xBingo) return 0;
        if(oBingo && (oCnt != xCnt+1)) return 0;
        if(xBingo && (oCnt != xCnt)) return 0;

        return 1;
    }

    public static boolean checkBingo(char c){
        for(int i = 0; i < 8; i++){
            boolean flag = true;
            for(int j = 0; j < 3; j++){
                if(map[bingo[i][j][0]][bingo[i][j][1]] != c) {
                    flag = false;
                    break;
                }
            }
            if(flag) return true;
        }

        return false;
    }

}
