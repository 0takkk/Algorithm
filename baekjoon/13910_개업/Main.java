import java.io.*;
import java.util.*;

public class Main {

    public static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[m];
        ArrayList<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            list.add(arr[i]);
        }

        for(int i = 0; i < m-1; i++){
            for(int j = i+1; j < m; j++){
                list.add(arr[i] + arr[j]);
            }
        }

        Collections.sort(list);

        int[] dp = new int[n+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 0; j < list.size(); j++){
                if(i < list.get(j)) break;
                else if(i == list.get(j)){
                    dp[i] = 1;
                    break;
                }
                else dp[i] = Math.min(dp[i], dp[i-list.get(j)]+1);
            }
        }

        if(dp[n] >= INF) System.out.println(-1);
        else System.out.println(dp[n]);
    }

}
