import java.io.*;
import java.util.*;

public class Main {

    public static class Pos{
        int x ,y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int r, c;
    public static char[][] map;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new char[r][c];
        for(int i = 0; i < r; i++){
            String str = br.readLine();
            for(int j = 0; j < c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        while(cnt++ < n){
            int high = Integer.parseInt(st.nextToken());

            int dir;
            if(cnt % 2 == 1) dir = 0;  // 왼쪽
            else dir = 1;  // 오른쪽

            shoot(high, dir);
            find();
        }

        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    public static void shoot(int high, int dir){
        if(dir == 0){
            for(int i = 0; i < c; i++){
                if(map[r-high][i] == 'x'){
                    map[r-high][i] = '.';
                    break;
                }
            }
        }
        else{
            for(int i = c-1; i >= 0; i--){
                if(map[r-high][i] == 'x'){
                    map[r-high][i] = '.';
                    break;
                }
            }
        }
    }

    public static void find(){
        boolean[][] visited = new boolean[r][c];
        Queue<Pos> q = new LinkedList<>();

        for(int i = 0; i < c; i++){
            if(map[r-1][i] == '.' || visited[r-1][i]) continue;

            visited[r-1][i] = true;
            q.offer(new Pos(r-1, i));

            while(!q.isEmpty()){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(nx < 0 || nx >= r || ny < 0 || ny >= c) continue;

                    if(!visited[nx][ny] && map[nx][ny] == 'x'){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny));
                    }
                }
            }
        }

        ArrayList<Pos> list = new ArrayList<>();
        for(int i = 0; i < r; i++){
            for(int j = 0; j < c; j++){
                if(!visited[i][j] && map[i][j] == 'x'){
                    list.add(new Pos(i, j));
                    map[i][j] = '.';
                }
            }
        }

        if(list.isEmpty()) return;

        down(list);
    }


    public static void down(ArrayList<Pos> list){
        boolean flag = true;

        while(flag){
            for(Pos p : list){
                int x = p.x + 1;
                int y = p.y;

                if(x < 0 || x >= r || y < 0 || y >= c || map[x][y] == 'x'){
                    flag = false;
                    break;
                }
            }

            if(flag){
                for(Pos p : list){
                    p.x++;
                }
            }
        }

        for(Pos p : list){
            map[p.x][p.y] = 'x';
        }
    }

}
