import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans;
    public static int[] alpa;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] input = br.readLine().toCharArray();
        n = input.length;
        alpa = new int[26];

        for (char c : input) {
            alpa[c-'a']++;
        }

        dfs(0, -1);

        System.out.println(ans);
    }

    public static void dfs(int idx, int pre){
        if(idx == n){
            ans++;
            return;
        }

        for(int i = 0; i < 26; i++){
            if(i == pre) continue;
            if(alpa[i] == 0) continue;

            alpa[i]--;
            dfs(idx+1, i);
            alpa[i]++;
        }
    }

}
