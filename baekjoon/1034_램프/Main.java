import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String[] table = new String[n];
        int[] zero = new int[n];

        for(int i = 0; i < n; i++){
            table[i] = br.readLine();
            for(int j = 0; j < m; j++){
                if(table[i].charAt(j) == '0') zero[i]++;
            }
        }

        int ans = 0;
        int k = Integer.parseInt(br.readLine());
        HashSet<String> visited = new HashSet<>();

        for(int i = 0; i < n; i++){
            if(visited.contains(table[i])) continue;
            visited.add(table[i]);

            if(zero[i] > k) continue;
            if(zero[i] % 2 != k % 2) continue;

            int cnt = 1;
            for(int j = i+1; j < n; j++){
                if(table[i].equals(table[j])) cnt++;
            }

            ans = Math.max(ans, cnt);
        }

        System.out.println(ans);
    }

}
