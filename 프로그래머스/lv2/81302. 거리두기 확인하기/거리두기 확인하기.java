import java.util.*;

class Solution {

    public static char[][] map;

    public static int[] dx = {0, 0, 1, -1, -1 ,-1, 1, 1, 0, 0, 2, -2};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1, 2, -2, 0, 0};

    public static ArrayList<Integer> solution(String[][] places) {
        ArrayList<Integer> ans = new ArrayList<>();

        for (String[] place : places) {
            boolean flag = true;
            map = new char[5][5];

            for(int i = 0; i < 5; i++){
                String s = place[i];
                for(int j = 0; j < 5; j++){
                    map[i][j] = s.charAt(j);
                }
            }

            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(map[i][j] == 'P'){
                        if(flag && !check(i, j)){
                            flag = false;
                            ans.add(0);
                        }
                    }
                }
            }

            if(flag) ans.add(1);
        }

        return ans;
    }

    public static boolean check(int x, int y){
        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] == 'P') return false;
        }

        for(int d = 4; d < 8; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] == 'P' && (map[nx][y] != 'X' || map[x][ny] != 'X')) return false;
        }

        for(int d = 8; d < 12; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(!isRange(nx, ny)) continue;

            if(map[nx][ny] == 'P' && map[(x+nx)/2][(y+ny)/2] != 'X') return false;
        }

        return true;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < 5 && y >= 0 && y < 5;
    }

}