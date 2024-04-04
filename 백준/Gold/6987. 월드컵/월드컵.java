import java.io.*;
import java.util.*;

public class Main {

    public static int[] team1 = {0,0,0,0,0,1,1,1,1,2,2,2,3,3,4};
    public static int[] team2 = {1,2,3,4,5,2,3,4,5,3,4,5,4,5,5};

    public static int[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = 4;
        StringBuilder sb = new StringBuilder();

        while(t-->0) {
            result = new int[6][3];
            st = new StringTokenizer(br.readLine());
            int total = 0;

            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 3; j++) {
                    result[i][j] = Integer.parseInt(st.nextToken());
                    total += result[i][j];
                }
            }

            if(total != 30) {
                sb.append(0).append(" ");
                continue;
            }

            sb.append(dfs(0) ? 1 : 0).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static boolean dfs(int idx) {
        if(idx == 15) {
            return true;
        }

        int t1 = team1[idx];
        int t2 = team2[idx];

        if(result[t1][0] > 0 && result[t2][2] > 0) {
            result[t1][0]--;
            result[t2][2]--;
            if(dfs(idx+1)) return true;
            result[t1][0]++;
            result[t2][2]++;
        }

        if(result[t1][1] > 0 && result[t2][1] > 0) {
            result[t1][1]--;
            result[t2][1]--;
            if(dfs(idx+1)) return true;
            result[t1][1]++;
            result[t2][1]++;
        }

        if(result[t1][2] > 0 && result[t2][0] > 0) {
            result[t1][2]--;
            result[t2][0]--;
            if(dfs(idx+1)) return true;
            result[t1][2]++;
            result[t2][0]++;
        }

        return false;
    }

}