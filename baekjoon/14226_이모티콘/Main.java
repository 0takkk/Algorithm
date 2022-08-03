import java.io.*;
import java.util.*;

public class Main {

    public static int s;

    public static class Node{
        int screen, board, time;

        public Node(int screen, int board, int time) {
            this.screen = screen;
            this.board = board;
            this.time = time;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        s = Integer.parseInt(br.readLine());

        System.out.println(bfs());
    }

    public static int bfs(){
        boolean[][] visited = new boolean[2001][2001];
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 0, 0));
        visited[1][0] = true;

        while(!q.isEmpty()){
            Node node = q.poll();
            int screen = node.screen;
            int board = node.board;
            int time = node.time;

            if(screen == s) return time;

            for(int i = 0; i < 3; i++){
                int newScreen;
                int newBoard;

                if(i == 0) {
                    newScreen = screen;
                    newBoard = screen;
                } else if(i == 1){
                    if(board == 0) continue;

                    newScreen = screen + board;
                    newBoard = board;
                } else{
                    newScreen = Math.max(screen-1, 0);
                    newBoard = board;
                }

                if(newScreen > 2000 || newBoard > 2000) continue;

                if(!visited[newScreen][newBoard]){
                    visited[newScreen][newBoard] = true;
                    q.offer(new Node(newScreen, newBoard, time+1));
                }
            }
        }

        return -1;
    }

}
