import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            int n = Integer.parseInt(br.readLine());

            map = new HashMap<>();
            for(int i = 0; i < n; i++){
                String[] s = br.readLine().split(" ");
                map.put(s[1], map.getOrDefault(s[1], 0) + 1);
            }

            long ans = 1;

            for (String key : map.keySet()) {
                ans *= (map.get(key)+1);
            }

            sb.append(ans-1 + "\n");
        }

        System.out.println(sb.toString());
    }


}
