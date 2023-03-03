import java.util.*;

public class Main {
    
    public static void main(String[] args) {
        System.out.println(solution(new String[] {"X591X","X1X5X","X231X", "1XXX1"}));
    }

    public static class Pos{
        int x, y;
        int sum;

        public Pos(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }
    }

    public static int n, m;
    public static char[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new char[n][m];

        for(int i = 0; i < n; i++){
            map[i] = maps[i].toCharArray();
        }

        visited = new boolean[n][m];

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != 'X' && !visited[i][j]){
                    visited[i][j] = true;
                    list.add(dfs(new Pos(i, j, (map[i][j] - '0'))));
                }
            }
        }

        if(list.size() == 0){
            return new int[] {-1};
        }

        Collections.sort(list);
        int[] answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }

        return answer;
    }

    public static int dfs(Pos p){
        int x = p.x;
        int y = p.y;
        int sum = p.sum;

        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny] && map[nx][ny] != 'X'){
                visited[nx][ny] = true;
                sum += dfs(new Pos(nx, ny, (map[nx][ny] - '0')));
            }
        }

        return sum;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
