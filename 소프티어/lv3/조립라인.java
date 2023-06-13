import java.util.*;
import java.io.*;


public class Main
{
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] work = new int[n][2];
        int[][] move = new int[n-1][2];

        for(int i = 0; i < n-1; i++){
            st = new StringTokenizer(br.readLine());
            work[i][0] = Integer.parseInt(st.nextToken());
            work[i][1] = Integer.parseInt(st.nextToken());
            move[i][0] = Integer.parseInt(st.nextToken());
            move[i][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        work[n-1][0] = Integer.parseInt(st.nextToken());
        work[n-1][1] = Integer.parseInt(st.nextToken());

        int[] dpA = new int[n];
        int[] dpB = new int[n];

        dpA[0] = work[0][0];
        dpB[0] = work[0][1];

        for(int i = 1; i < n; i++){
            int a = work[i][0];
            int b = work[i][1];
            int aToB = move[i-1][0];
            int bToA = move[i-1][1];

            dpA[i] = Math.min(dpA[i-1] + a, dpB[i-1] + bToA + a);
            dpB[i] = Math.min(dpB[i-1] + b, dpA[i-1] + aToB + b);
        }

        System.out.println(Math.min(dpA[n-1], dpB[n-1]));
    }

}
