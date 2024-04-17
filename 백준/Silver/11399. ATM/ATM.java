import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        int ans = 0;

        for(int i = 1; i <= n; i++){
            arr[i] += arr[i-1];
            ans += arr[i];
        }

        System.out.println(ans);
    }
}
