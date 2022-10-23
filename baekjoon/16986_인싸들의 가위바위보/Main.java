import java.io.*;
import java.util.*;

public class Main {

    public static int n, k;
    public static int[][] map, rock;
    public static boolean[] visited;
    public static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rock = new int[3][21];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= 20; i++){
            rock[1][i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= 20; i++){
            rock[2][i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[n+1];

        dfs(1);

        System.out.println(flag ? 1 : 0);
    }

    public static void dfs(int idx){
        if(flag) return;

        if(idx == n+1){
            flag = play();
            return;
        }

        for(int i = 1; i <= n; i++){
            if(!visited[i]){
                visited[i] = true;
                rock[0][idx] = i;
                dfs(idx+1);
                visited[i] = false;
            }
        }
    }

    public static boolean play(){
        int[] win = new int[3];
        int[] act = new int[3];
        Arrays.fill(act, 1);

        int p1 = 0, p2 = 1;

        while(true){
            int nextP = 3 - p1 - p2;

            if(win[0] == k) return true;
            else if(win[1] == k || win[2] == k) return false;

            if(act[0] == n+1 || act[1] == 21 || act[2] == 21) return false;

            int winner = whoIsWinner(p1, p2, act);
            win[winner]++;
            act[p1]++;
            act[p2]++;

            p1 = winner;
            p2 = nextP;
        }
    }

    public static int whoIsWinner(int p1, int p2, int[] act){
        int act1 = rock[p1][act[p1]];
        int act2 = rock[p2][act[p2]];

        int result = map[act1][act2];
        if(result == 2) return p1;
        else if(result == 1) return Math.max(p1, p2);
        else return p2;
    }
}
