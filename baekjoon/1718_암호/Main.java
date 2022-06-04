import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String text = br.readLine();
        String key = br.readLine();

        int idx = 0;
        int size = key.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < text.length(); i++){
            char t = text.charAt(i);
            char k = key.charAt(idx);

            if(t == ' '){
                sb.append(' ');
            }else{
                int cipher = t-k;
                if(cipher > 0){
                    sb.append((char)(cipher + 'a'-1));
                } else{
                    sb.append((char)('z' + cipher));
                }
            }

            idx = (idx+1) % size;
        }

        System.out.println(sb.toString());
    }

}
