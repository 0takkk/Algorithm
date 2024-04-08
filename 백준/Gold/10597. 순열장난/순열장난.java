import java.io.*;
import java.util.*;

public class Main {

    public static String input;
    public static int n;
    public static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        input = br.readLine();
        n = input.length();
        visited = new boolean[51];

        dfs(0,0, "");
    }

    public static void dfs(int idx, int max, String ans){
        if(idx == n){
            for(int i = 1; i <= max; i++){
                if(!visited[i]) return;
            }
            System.out.println(ans.trim());
            System.exit(0);
            return;
        }

        int num = Integer.parseInt(input.substring(idx, idx+1));
        if(!visited[num]){
            visited[num] = true;
            dfs(idx+1, Math.max(max, num), ans + " " + num);
            visited[num] = false;
        }

        if(idx < n-1){
            num = Integer.parseInt(input.substring(idx, idx+2));
            if(num <= 50 && !visited[num]){
                visited[num] = true;
                dfs(idx+2, Math.max(max, num), ans + " " + num);
                visited[num] = false;
            }
        }
    }
}
