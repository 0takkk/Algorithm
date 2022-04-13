import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, time;
    public static int[][] box;
    public static Queue<Pos> ripenTomatoes;
    public static ArrayList<Pos> tomatoes;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos{
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        ripenTomatoes = new LinkedList<>();
        tomatoes = new ArrayList<>();

        box = new int[n][m];
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++){
                box[i][j] = Integer.parseInt(st.nextToken());

                if(box[i][j] == 1) ripenTomatoes.offer(new Pos(i, j));
                else if(box[i][j] == 0) tomatoes.add(new Pos(i, j));
            }
        }

        if(tomatoes.size() == 0){
            System.out.println(0);
        } else{
            dfs();

            boolean flag = false;
            for(int i = 0; i < tomatoes.size(); i++){
                Pos p = tomatoes.get(i);

                if(box[p.x][p.y] == 0){
                    flag = true;
                    break;
                }
            }

            if(flag) System.out.println(-1);
            else System.out.println(time-1);
        }
    }

    public static void dfs(){
        while(!ripenTomatoes.isEmpty()){
            int size = ripenTomatoes.size();
            for(int i = 0; i < size; i++){
                Pos p = ripenTomatoes.poll();
                int x = p.x;
                int y = p.y;

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                    if(box[nx][ny] == 0){
                        box[nx][ny] = 1;
                        ripenTomatoes.offer(new Pos(nx, ny));
                    }
                }
            }

            time++;
        }
    }

}
