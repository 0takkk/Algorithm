import java.io.*;
import java.util.*;

public class Main {

    public static int n, m;
    public static boolean[] isPrime;
    public static int[] arr;
    public static HashSet<Integer> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        getPrime();
        set = new HashSet<>();

        dfs(0, 0, 0);

        if(set.size() == 0){
            System.out.println(-1);
            return;
        }

        int[] ans = new int[set.size()];
        int idx = 0;
        for (int prime : set) {
            ans[idx++] = prime;
        }

        Arrays.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (int prime : ans) {
            sb.append(prime).append(" ");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int cnt, int sum){
        if(cnt == m){
            if(isPrime[sum]) set.add(sum);
            return;
        }

        for(int i = idx; i < n; i++){
            dfs(i+1, cnt+1, sum + arr[i]);
        }
    }

    public static void getPrime(){
        isPrime = new boolean[9001];
        Arrays.fill(isPrime, true);

        isPrime[0] = isPrime[1] = false;
        for(int i = 2; i <= Math.sqrt(9000); i++){
            if(!isPrime[i]) continue;
            for(int j = i+i; j <= 9000; j+=i){
                isPrime[j] = false;
            }
        }
    }

}
