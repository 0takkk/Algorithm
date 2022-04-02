import java.util.*;

public class Main {
    public static void main(String[] args) {
//        System.out.println(solution(new String[] {"??b", "abc", "cc?"}));
        System.out.println(solution(new String[] {"abcabcab","????????"}));
//        System.out.println(solution(new String[] {"aa?"}));

    }

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(x : " + x + ", y : " + y + ")";
        }
    }

    public static int n,m, answer;
    public static char[][] map;
    public static ArrayList<Pos> list;

    public static char[] alpa = {'a', 'b', 'c'};
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};

    public static int solution(String[] grid) {
        answer = 0;

        n = grid.length;
        m = grid[0].length();

        map = new char[n][m];
        list = new ArrayList<>();

        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j < grid[i].length(); j++){
                map[i][j] = grid[i].charAt(j);

                if(map[i][j] == '?') list.add(new Pos(i,j));
            }
        }

        System.out.println(list );

        for (char[] chars : map) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        dfs(0);

        return answer;
    }

    public static void dfs(int idx){
        if(idx == list.size()){
            boolean[][] visited = new boolean[n][m];
            int count = 0;

            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    if(!visited[i][j]){
                        count += bfs(new Pos(i, j), visited);
                        if(count > 3) return;
                    }
                }
            }

            answer++;
            return;
        }

        for(int i = idx; i < list.size(); i++){
            Pos p = list.get(i);
            int x = p.x;
            int y = p.y;

            for(int j = 0; j < 3; j++){
                map[x][y] = alpa[j];
                dfs(idx+1);
            }
        }
    }

    public static int bfs(Pos pos, boolean[][] visited){
        Queue<Pos> q = new LinkedList<>();
        q.offer(pos);
        visited[pos.x][pos.y] = true;
        char c = map[pos.x][pos.y];

        while(!q.isEmpty()){
            Pos p = q.poll();
            int x = p.x;
            int y = p.y;

            for(int i = 0; i < 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(!isRange(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == c){
                    visited[nx][ny] = true;
                    q.offer(new Pos(nx, ny));
                }
            }
        }

        return 1;
    }


//
//    public static void preCheck(){
//        for(int i = 0; i < list.size(); i++){
//            Pos p = list.get(i);
//            int x = p.x;
//            int y = p.y;
//
//            for(int k = 0; k < 3; k++){
//                boolean flag = false;
//
//                for(int j = 0; j < 4; j++) {
//                    int nx = x + dx[j];
//                    int ny = y + dy[j];
//
//                    if (!isRange(nx, ny)) continue;
//
//                    flag = true;
//                    if (map[nx][ny] != alpa[k]) {
//                        flag = false;
//                        break;
//                    }
//                }
//
//                if(flag) {
//                    map[x][y] = alpa[k];
//                    list.remove(i);
//                }
//            }
//        }
//    }

    public static boolean isRange(int x, int y){
        if(x < 0 || x >= n || y < 0 || y >= m) return false;
        return true;
    }

}

