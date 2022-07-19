import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < 10; i++){
            map.put((char)('0' + i), i);
        }
        for(int i = 10; i <= 35; i++){
            map.put((char)('A'-10+i), i);
        }

        st = new StringTokenizer(br.readLine());
        String n = st.nextToken();
        int b = Integer.parseInt(st.nextToken());

        int ans = 0;
        int size = n.length();
        for(int i = size-1; i >= 0; i--){
            ans += (map.get(n.charAt(i)) *  Math.pow(b, size-1-i));
        }

        System.out.println(ans);
    }

}
