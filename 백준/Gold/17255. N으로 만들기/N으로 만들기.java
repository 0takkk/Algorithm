import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans;
    public static int[] arr;
    public static HashSet<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();
        n = str.length();
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = str.charAt(i) - '0';
        }
        set = new HashSet<>();

        for(int i = 0; i < n; i++) {
            dfs(i, i, arr[i]+"", arr[i]+"");
        }

        System.out.println(set.size());
    }

    public static void dfs(int left, int right, String num, String path) {
        if(left == 0 && right == n-1) {
            set.add(path);
            return;
        }

        if(left > 0) {
            dfs(left-1, right, arr[left-1]+num, path + " " + arr[left-1]+num);
        }

        if(right < n-1) {
            dfs(left, right+1, num+arr[right+1], path + " " + num+arr[right+1]);
        }
    }

}