import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];

        for(int i = 0; i < n; i++){
            map[i] = br.readLine().toCharArray();
        }

        String ans = "zzzzzzzzzzzzzzzzzzzz";

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(String.valueOf(map[i]), "#");

            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(str.length() >= 2 && str.compareTo(ans) < 0) ans = str;
            }
        }

        for(int j = 0; j < m; j++){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
                sb.append(map[i][j]);
            }

            st = new StringTokenizer(sb.toString(), "#");
            while(st.hasMoreTokens()){
                String str = st.nextToken();
                if(str.length() >= 2 && str.compareTo(ans) < 0) ans = str;
            }
        }

        System.out.println(ans);
    }

}
