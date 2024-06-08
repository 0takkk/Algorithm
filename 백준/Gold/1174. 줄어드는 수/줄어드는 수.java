import java.io.*;
import java.util.*;

public class Main {

    public static int[] nums = {9,8,7,6,5,4,3,2,1,0};
    public static HashSet<Long> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        set = new HashSet<>();

        dfs(0, 0);

        if(set.size() < n) {
            System.out.println(-1);
        }
        else {
            ArrayList<Long> result = new ArrayList<>(set);
            Collections.sort(result);
            System.out.println(result.get(n-1));
        }
    }

    public static void dfs(int idx, long num) {
        set.add(num);

        if(idx >= 10) return;

        dfs(idx+1, num*10 + nums[idx]);
        dfs(idx+1, num);
    }
}
