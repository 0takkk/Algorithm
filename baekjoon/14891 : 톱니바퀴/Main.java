import java.io.*;
import java.util.*;

public class Main {

    public static int[][] wheel = new int[5][8];
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 1; i <= 4; i++){
            String str = br.readLine();
            for(int j = 0; j < 8; j++){
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        int k = Integer.parseInt(br.readLine());

        while(k-->0){
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            visited = new boolean[5];
            rotate(idx, dir);
        }

        int result = 0;
        for(int i = 1; i <= 4; i++){
            if(wheel[i][0] == 1){
                result += Math.pow(2, i-1);
            }
        }

        System.out.println(result);
    }

    public static void rotate(int idx, int dir){
        if(visited[idx]) return;
        visited[idx] = true;

        int left = wheel[idx][6];
        int right = wheel[idx][2];

        if(dir == 0) return;
        else if(dir == 1){
            int tmp = wheel[idx][7];
            for(int i = 7; i > 0; i--){
                wheel[idx][i] = wheel[idx][i-1];
            }
            wheel[idx][0] = tmp;
        }
        else{
            int tmp = wheel[idx][0];
            for(int i = 0; i < 7; i++){
                wheel[idx][i] = wheel[idx][i+1];
            }
            wheel[idx][7] = tmp;
        }

        if(idx - 1 > 0){
            int newDir = wheel[idx-1][2] == left ? 0 : (dir == 1 ? -1 : 1);
            rotate(idx-1, newDir);
        }
        if(idx + 1 < 5){
            int newDir = wheel[idx+1][6] == right ? 0 : (dir == 1 ? -1 : 1);
            rotate(idx+1, newDir);
        }

    }

}
