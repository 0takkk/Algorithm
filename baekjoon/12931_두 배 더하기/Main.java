import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] b = new int[n];
        int[][] count = new int[n][2];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            b[i] = Integer.parseInt(st.nextToken());

            int num = b[i];
            while(num > 0){
                if(num % 2 == 1){
                    num--;
                    count[i][0]++;
                }else{
                    num /= 2;
                    count[i][1]++;
                }
            }
        }

        int ans = 0;
        int max = 0;
        for(int i = 0; i < n; i++){
            ans += count[i][0];
            max = Math.max(max, count[i][1]);
        }

        System.out.println(ans + max);
    }

}
