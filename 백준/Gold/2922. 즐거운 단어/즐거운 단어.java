import java.io.*;
import java.util.*;

public class Main {

    public static HashSet<Character> vowels = new HashSet<>(List.of('A', 'E', 'I', 'O', 'U'));
    public static int n;
    public static char[] arr;
    public static long ans = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String input = br.readLine();
        n = input.length();
        arr = new char[n];
        boolean haveL = false;

        for(int i = 0; i < n; i++) {
            arr[i] = input.charAt(i);
            if(arr[i] == 'L') haveL = true;
        }

        dfs(0, 1, haveL, 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int idx, long cnt, boolean haveL, int vowelCnt, int consonantCnt) {
        if(vowelCnt == 3 || consonantCnt == 3) return;
        if(idx == n) {
            if(haveL) ans += cnt;
            return;
        }

        char c = arr[idx];
        if(c == '_') {
            dfs(idx+1, cnt * 5, haveL, vowelCnt+1, 0);
            dfs(idx+1, cnt * 20, haveL, 0, consonantCnt+1);
            dfs(idx+1, cnt, true, 0, consonantCnt+1);
        }
        else {
            if(vowels.contains(c)) {
                dfs(idx+1, cnt, haveL, vowelCnt+1, 0);
            }
            else {
                dfs(idx+1, cnt, haveL, 0, consonantCnt+1);
            }
        }
    }

}