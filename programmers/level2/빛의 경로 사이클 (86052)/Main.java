package com.company;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"SL", "LR"}));
    }

    public static int n, m;
    public static boolean[][][] visited;

    public static int[] dx = {1, 0, -1, 0};
    public static int[] dy = {0, -1, 0, 1};

    public static int[] solution(String[] grid) {
        ArrayList<Integer> ans = new ArrayList<>();

        n = grid.length;
        m = grid[0].length();

        visited = new boolean[n][m][4];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < 4; k++){
                    if(!visited[i][j][k]){
                        ans.add(shoot(i, j, k, grid));
                    }
                }
            }
        }

        Collections.sort(ans);

        int[] answer = new int[ans.size()];
        for(int i = 0; i < answer.length; i++)
            answer[i] = ans.get(i);

        return answer;
    }

    public static int shoot(int x, int y, int k, String[] grid){
        int cnt = 0;

        while(true){
            if(visited[x][y][k]) break;

            cnt++;
            visited[x][y][k] = true;

            if(grid[x].charAt(y) == 'L'){
                k = k == 0 ? 3 : k - 1;
            }
            else if(grid[x].charAt(y) == 'R'){
                k = k == 3 ? 0 : k + 1;
            }

            x = (x + dx[k] + n) % n;
            y = (y + dy[k] + m) % m;
        }

        return cnt;
    }

}
