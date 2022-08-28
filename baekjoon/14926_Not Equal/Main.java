import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        boolean[][] visited = new boolean[n+1][n+1];

        StringBuilder sb = new StringBuilder();
        int size = n*(n-1) / 2;

        int now = 0;

        visited[1][n] = visited[n][1] = true;
        while(size-->0){
            for(int i = 1; i <= n; i++){
                if(now == i || visited[now][i]) continue;
                visited[now][i] = visited[i][now] = true;
                now = i;
                break;
            }
            sb.append("a").append(now).append(" ");
        }

        sb.append("a1");
        System.out.println(sb.toString());
    }
    
}
