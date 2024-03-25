import java.io.*;
import java.util.*;

public class Main {

    public static char[] quack = {'q', 'u', 'a', 'c', 'k'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        char[] arr = br.readLine().toCharArray();
        int len = arr.length;
        int ans = 0;

        boolean[] visited = new boolean[arr.length];

        int turn = 0;

        for(int i = 0; i < len; i++) {
            if(!visited[i]) {
                for (int j = i; j < len; j++) {
                    if(!visited[j] && arr[j] == quack[turn]) {
                        visited[j] = true;
                        if(++turn == 5) turn = 0;
                    }
                }
                if(turn != 0) {
                    System.out.println(-1);
                    return;
                }
                else {
                    ans++;
                }
            }
        }
        
        for(int i = 0; i < len; i++) {
            if(!visited[i]) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ans);
    }

}