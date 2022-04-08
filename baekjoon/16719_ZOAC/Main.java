import java.io.*;
import java.util.*;

public class Main {

    public static String input;
    public static boolean[] visited;
    public static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        input = br.readLine();
        visited = new boolean[input.length()];

        sb = new StringBuilder();

        zoac(0, input.length()-1);
        System.out.println(sb.toString());
    }

    public static void zoac(int left, int right){
        if(left > right) return;

        int idx = left;
        for(int i = left; i <= right; i++){
            if(input.charAt(i) < input.charAt(idx)){
                idx = i;
            }
        }

        visited[idx] = true;
        for(int i = 0; i < input.length(); i++){
            if(visited[i]){
                sb.append(input.charAt(i));
            }
        }
        sb.append("\n");

        zoac(idx+1, right);
        zoac(left, idx-1);
    }
    
}
