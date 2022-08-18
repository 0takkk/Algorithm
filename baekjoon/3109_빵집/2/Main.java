import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int n, m, ans;
    public static char[][] map;

    public static int[] dx = {-1, 0, 1};
    public static int[] dy = {1, 1, 1};  // 오른쪽 위, 오른쪽, 오른쪽 아래 순으로 방문

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        for(int i = 0; i < n; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
            }
        }

        dfs();
        System.out.println(ans);
    }

    public static void dfs(){
        for(int i = 0; i < n; i++){
            Stack<Pos> stack = new Stack<>();
            stack.push(new Pos(i, 0));

            while(!stack.isEmpty()){
                Pos p = stack.pop();
                int x = p.x;
                int y = p.y;

                map[x][y] = 'x';

                if(y == m-1){
                    ans++;
                    break;
                }

                for(int j = 2; j >= 0; j--){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] == '.'){
                        stack.push(new Pos(nx, ny));
                    }
                }
            }
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

}
