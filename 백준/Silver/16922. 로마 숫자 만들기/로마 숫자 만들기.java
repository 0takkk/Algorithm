import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static HashSet<Integer> set;
    public static int[] num = {1, 5, 10, 50};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        set = new HashSet<>();

        dfs(0, 0, 0);

        System.out.println(set.size());
    }

    public static void dfs(int cnt, int idx, int sum) {
        if(cnt == n) {
            set.add(sum);
            return;
        }

        for(int i = idx; i < 4; i++) {
            dfs(cnt+1, i, sum+num[i]);
        }
    }

}