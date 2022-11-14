import java.io.*;
import java.util.*;

public class Main {

    public static class Node{
        int idx, x, y, dir;

        public Node(int idx, int x, int y, int dir) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    '}';
        }
    }

    public static int n, k, time = 1;
    public static int[][] map;
    public static Node[] nodes;
    public static LinkedList<Node>[][] list;

    public static int[] dx = {0, 0, 0, -1, 1};
    public static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        list = new LinkedList[n+1][n+1];

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                list[i][j] = new LinkedList<>();
            }
        }

        nodes = new Node[k+1];
        for(int i = 1; i <= k; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            nodes[i] = new Node(i, x, y, dir);
            list[x][y].add(nodes[i]);
        }

        play();

        System.out.println(time > 1000 ? -1 : time);

    }

    public static void play(){
        while(true){
            for(int i = 1; i <= k; i++){
                move(i);

                Node node = nodes[i];
                int x = node.x;
                int y = node.y;

                if(list[x][y].size() >= 4) return;
            }
            time++;

            if(time > 1000) return;
        }
    }

    public static void move(int idx){
        Node node = nodes[idx];
        int x = node.x;
        int y = node.y;
        int dir = node.dir;

        Deque<Node> dq = new ArrayDeque<>();
        LinkedList<Node> nodeList = list[x][y];
        boolean flag = false;

        int start = 0;
        for(int i = 0; i < nodeList.size(); i++){
            Node n = nodeList.get(i);
            if(n.idx == idx) {
                flag = true;
                start = i;
            }

            if(flag){
                dq.offer(n);
            }
        }

        for(int i = nodeList.size()-1; i >= start; i--){
            nodeList.remove(i);
        }

        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // white
        if(mapColor(nx, ny) == 0){
            moveToWhite(dq, nx, ny);
        }
        // red
        else if(mapColor(nx, ny) == 1){
            moveToRed(dq, nx, ny);
        }
        // blue
        else{
            node.dir = changeDir(dir);

            int nnx = x + dx[node.dir];
            int nny = y + dy[node.dir];

            // white
            if(mapColor(nnx, nny) == 0){
                moveToWhite(dq, nnx, nny);
            }
            // red
            else if(mapColor(nnx, nny) == 1){
                moveToRed(dq, nnx, nny);
            }
            else{
                while(!dq.isEmpty()){
                    list[x][y].add(dq.pollFirst());
                }
            }
        }

    }

    private static void moveToRed(Deque<Node> dq, int nx, int ny) {
        while(!dq.isEmpty()){
            Node n = dq.pollLast();
            n.x = nx;
            n.y = ny;
            list[nx][ny].add(n);
        }
    }

    private static void moveToWhite(Deque<Node> dq, int nx, int ny) {
        while(!dq.isEmpty()){
            Node n = dq.pollFirst();
            n.x = nx;
            n.y = ny;
            list[nx][ny].add(n);
        }
    }

    public static int mapColor(int x, int y){
        if(!isRange(x, y) || map[x][y] == 2) return 2;
        else if(map[x][y] == 1) return 1;
        else return 0;
    }

    public static boolean isRange(int x, int y){
        return x > 0 && x <= n && y > 0 && y <= n;
    }

    public static int changeDir(int dir){
        if(dir == 1) return 2;
        else if(dir == 2) return 1;
        else if(dir == 3) return 4;
        else return 3;
    }

}
