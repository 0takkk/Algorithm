import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static char[][] map;
    public static boolean[][] visited, isPossible;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];

        for(int i = 0; i < n; i++){
            map[i] = br.readLine().toCharArray();
        }

        visited = new boolean[n][m];
        isPossible = new boolean[n][m];

        int ans = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j]) dfs(i, j);
                if(isPossible[i][j]) ans++;
            }
        }

        System.out.println(ans);
    }

    public static boolean dfs(int x, int y){
        visited[x][y] = true;

        int dir = getDir(map[x][y]);
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        if(!isRange(nx, ny)) {
            return isPossible[x][y] = true;
        }

        if(visited[nx][ny]) return isPossible[x][y] = isPossible[nx][ny];
        else return isPossible[x][y] = dfs(nx, ny);
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    public static int getDir(char c){
        if(c == 'U') return 0;
        else if(c == 'R') return 1;
        else if(c == 'D') return 2;
        else return 3;
    }
}
