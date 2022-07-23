import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[] down = new int[h+1];
        int[] up = new int[h+1];

        for(int i = 0; i < n; i++){
            int height = Integer.parseInt(br.readLine());

            if(i % 2 == 0) down[height]++;
            else up[height]++;
        }

        for(int i = h-1; i > 0; i--){
            down[i] += down[i+1];
            up[i] += up[i+1];
        }

        int min = Integer.MAX_VALUE;
        int cnt = 0;

        for(int i = 1; i <= h; i++){
            int breakCnt = down[i] + up[h-i+1];

            if(breakCnt <= min){
                if(breakCnt == min){
                    cnt++;
                }
                else{
                    min = breakCnt;
                    cnt = 1;
                }
            }
        }

        System.out.println(min + " " + cnt);
    }

}
