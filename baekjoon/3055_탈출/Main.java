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

    public static int n, m;
    public static char[][] map;
    // 물이 번지는 것을 bfs로 처리할 때 사용할 큐
    // 새로 번진 물만 처리할 것.
    // 매번 물이 차있는 곳을 보면 중복 발생
    public static Queue<Pos> water = new ArrayDeque<>();

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];

        Pos p = null;  // 고슴도치 위치
        for(int i = 0; i < n ; i++){
            String str = br.readLine();
            for(int j = 0; j < m; j++){
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'S') {
                    p = new Pos(i, j);  // 고슴도치 위치 저장
                    map[i][j] = '.';  // 물이 번질 수 있도록 .으로 변경
                }
                else if(map[i][j] == '*') water.offer(new Pos(i, j));  // 물 누적
            }
        }

        int time = bfs(p);  // bfs
        if(time == -1) System.out.println("KAKTUS");  // bfs 결과가 -1이면 목적지에 도착할 수 없음
        else System.out.println(time);
    }

    public static int bfs(Pos pos){
        boolean[][] visited = new boolean[n][m];
        Queue<Pos> q = new ArrayDeque<>();  // 고슴도치 위치를 추가할 큐
        q.offer(pos);  // 고슴도치 위치 추가
        visited[pos.x][pos.y] = true;

        int time = 0;
        while(!q.isEmpty()){
            int size = q.size();  // 현재 고슴도치 큐 사이즈만큼 반복 -> 한 턴에 발생
            while(size-->0) {
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                if(map[x][y] == '*') continue;  // 현재 위치에 물이 차게되면 이 경우는 불가능 -> 패스
                if(map[x][y] == 'D') return time;  // 목적지에 도착하면 현재 time 리턴 후 종료

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (!isRange(nx, ny)) continue;

                    // 아직 방문 안했으면서, 빈칸이거나 목적지면 큐에 추가
                    if (!visited[nx][ny] && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
                        visited[nx][ny] = true;  // 방문 처리
                        q.offer(new Pos(nx, ny));
                    }
                }
            }

            size = water.size();  // 현재 새롭게 추가된 물의 사이즈 반큼 반복 -> 한 턴에 발생
            while(size-->0){
                Pos p = water.poll();
                int x = p.x;
                int y = p.y;

                for(int i = 0; i < 4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(!isRange(nx, ny)) continue;

                    if(map[nx][ny] == '.'){  // 빈칸이라면 
                        map[nx][ny] = '*';  // 방문 처리 대신 map에 물이 찼다라고 표시
                        water.offer(new Pos(nx, ny));
                    }
                }
            }

            time++;
        }

        return -1;  // 방문하지 못한다면 return -1
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}
