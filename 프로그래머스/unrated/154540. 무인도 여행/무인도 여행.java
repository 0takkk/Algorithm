import java.util.*;

class Solution {
    
    public static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m;
    public static int[][] map;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = maps[i].charAt(j) == 'X' ? 0 : maps[i].charAt(j) - '0';
            }
        }

        ArrayList<Integer> ans = new ArrayList<>();

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(map[i][j] != 0 && !visited[i][j]){
                    ans.add(bfs(new Pos(i, j)));
                }
            }
        }

        if(ans.size() == 0){
            return new int[]{-1};
        }
        
        Collections.sort(ans);
        int[] answer = new int[ans.size()];
        for(int i = 0; i < ans.size(); i++){
            answer[i] = ans.get(i);
        }

        return answer;
    }

    public static int bfs(Pos pos){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        int sum = 0;

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            sum += map[x][y];

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(isRange(nx, ny) && map[nx][ny] != 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return sum;
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}