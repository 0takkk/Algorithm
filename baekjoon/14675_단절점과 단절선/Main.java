import java.io.*;
import java.util.*;

public class Main {

    public static ArrayList<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i = 1; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        StringBuilder sb = new StringBuilder();
        int q = Integer.parseInt(br.readLine());
        while(q-->0){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 1){
                int count = 0;
                for(int next : list[b]){
                    count++;
                }
                if(count >= 2) sb.append("yes\n");
                else sb.append("no\n");
            } else {
                sb.append("yes\n");
            }
        }

        System.out.println(sb.toString());
    }

}
