import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x, y, len;
        String str;

        public Pos(int x, int y, int len, String str) {
            this.x = x;
            this.y = y;
            this.len = len;
            this.str = str;
        }
    }

    public static int n, m, maxLen;
    public static char[][] map;
    public static HashMap<String, Integer> count;

    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {1, -1, 0, 0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        for(int i = 0; i < n; i++){
            map[i] = br.readLine().toCharArray();
        }

        String[] keys = new String[k];
        count = new HashMap<>();

        for(int i = 0; i < k; i++){
            keys[i] = br.readLine();
            maxLen = Math.max(maxLen, keys[i].length());
            count.put(keys[i], 0);
        }

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                bfs(i, j);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String s : keys) {
            sb.append(count.get(s)).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void bfs(int x, int y){
        Queue<Pos> q = new ArrayDeque<>();
        q.offer(new Pos(x, y, 1, map[x][y]+""));

        while(!q.isEmpty()){
            Pos p = q.poll();

            if(count.containsKey(p.str)){
                count.put(p.str, count.get(p.str)+1);
            }

            if(p.len >= maxLen) continue;

            for(int i = 0; i < 8; i++){
                int nx = (p.x + dx[i]) % n;
                int ny = (p.y + dy[i]) % m;

                if(nx < 0) nx += n;
                if(ny < 0) ny += m;

                q.offer(new Pos(nx, ny, p.len+1, p.str + map[nx][ny]));
            }
        }
    }

}
