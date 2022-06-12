import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] num, arr;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            String str = br.readLine();

            if(str.charAt(0) == '0') break;

            st = new StringTokenizer(str);

            n = Integer.parseInt(st.nextToken());
            num = new int[50];
            arr = new int[6];

            for(int i = 0; i < n; i++){
                num[Integer.parseInt(st.nextToken())]++;
            }

            backtracking(1, 0);
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void backtracking(int idx, int cnt){
        if(cnt == 6){
            for(int i = 0; i < 6; i++){
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i = idx; i < 50; i++){
            if(num[i] > 0){
                num[i]--;
                arr[cnt] = i;
                backtracking(i+1, cnt+1);
                num[i]++;
            }
        }
    }

}
