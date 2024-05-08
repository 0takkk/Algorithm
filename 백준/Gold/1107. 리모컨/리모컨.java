import java.io.*;
import java.util.*;

public class Main {

    public static int target;
    public static boolean[] blocked;
    public static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        target = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        blocked = new boolean[10];
        if(m > 0) {
            st = new StringTokenizer(br.readLine());
            while(m-->0) {
                blocked[Integer.parseInt(st.nextToken())] = true;
            }
        }

        ans = Math.abs(target - 100);

        if(ans != 0) {
            dfs(0, 0);
        }

        System.out.println(ans);
    }

    public static void dfs(int idx, int number) {
        for(int i = 0; i < 10; i++) {
            if(!blocked[i]) {
                int newNumber = number * 10 + i;
                int cnt = Math.abs(target - newNumber) + String.valueOf(newNumber).length();
                ans = Math.min(ans, cnt);

                if(idx < 6) {
                    dfs(idx+1, newNumber);
                }
            }
        }
    }

}