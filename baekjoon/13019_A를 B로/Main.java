import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String a = br.readLine();
        String b = br.readLine();

        int[] alpa = new int[26];
        for(int i = 0; i < a.length(); i++){
            alpa[a.charAt(i) - 'A']++;
            alpa[b.charAt(i) - 'A']--;
        }

        for(int i = 0; i < 26; i++){
            if(alpa[i] != 0){
                System.out.println(-1);
                return;
            }
        }

        int size = a.length();
        int cnt = 0;

        for(int i = size-1; i >= 0; i--){
            if(a.charAt(i) == b.charAt(size-1-cnt)){
                cnt++;
            }
        }

        System.out.println(size - cnt);
    }

}
