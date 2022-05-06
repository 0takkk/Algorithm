import java.io.*;
import java.util.*;

public class Main {

    public static int n, m, fuel;
    public static int[][] map;
    public static int[][] passengers;

    public static Pos taxi;
    public static Pos[] destinations;

    public static Pos passenger, destination;

    public static int[] dx = {0, 0, 1, -1};
    public static int[] dy = {1, -1, 0, 0};

    public static class Pos implements Comparable<Pos>{
        int num;
        int x, y;
        int dist;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pos(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        public Pos(int num, int x, int y, int dist) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos o) {
            if(this.dist == o.dist){
                if(this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        passengers = new int[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        taxi = new Pos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        destinations = new Pos[m+1];

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            passengers[x1][y1] = i;
            destinations[i] = new Pos(x2, y2);
        }

        boolean flag = true;
        for(int i = 0; i < m; i++){
            if(findPassenger(taxi)){
                int num = passenger.num;
                int dist = passenger.dist;

                if((fuel -= dist) <= 0){
                    flag = false;
                    break;
                }

                if(findDestination(taxi, num)){
                    if((fuel -= destination.dist) < 0){
                        flag = false;
                        break;
                    }

                    fuel += destination.dist * 2;
                } else{
                    flag = false;
                    break;
                }

            } else{
                flag = false;
                break;
            }
        }

        if(flag) System.out.println(fuel);
        else System.out.println(-1);
    }

    public static boolean findPassenger(Pos pos){
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[n+1][n+1];
        pq.offer(pos);
        visited[pos.x][pos.y] = true;

        while(!pq.isEmpty()){
            int size = pq.size();
            for(int i = 0; i < size; i++) {
                Pos p = pq.poll();
                int x = p.x;
                int y = p.y;

                if(passengers[x][y] != 0){
                    passenger = new Pos(passengers[x][y], x, y, p.dist);
                    taxi = new Pos(x, y);
                    passengers[x][y] = 0;
                    return true;
                }

                for (int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(!isRange(nx, ny)) continue;

                    if(!visited[nx][ny] && map[nx][ny] == 0){
                        visited[nx][ny] = true;
                        pq.offer(new Pos(nx, ny, p.dist+1));
                    }
                }
            }
        }

        return false;
    }

    public static boolean findDestination(Pos pos, int num){
        Queue<Pos> q = new LinkedList<>();
        boolean[][] visited = new boolean[n+1][n+1];
        q.offer(pos);
        visited[pos.x][pos.y] = true;

        Pos dest = destinations[num];

        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                Pos p = q.poll();
                int x = p.x;
                int y = p.y;

                if(x == dest.x && y == dest.y){
                    destination = new Pos(num, x, y, p.dist);
                    taxi = new Pos(x, y);
                    return true;
                }

                for(int j = 0; j < 4; j++){
                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if(!isRange(nx, ny)) continue;

                    if(!visited[nx][ny] && map[nx][ny] == 0){
                        visited[nx][ny] = true;
                        q.offer(new Pos(nx, ny, p.dist+1));
                    }
                }
            }
        }

        return false;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= n;
    }

}
