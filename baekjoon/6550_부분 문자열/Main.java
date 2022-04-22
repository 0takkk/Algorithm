import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str="";
        while((str = br.readLine()) != null){
            st = new StringTokenizer(str);

            String s = st.nextToken();
            String t = st.nextToken();

            int idx = 0;

            for(int i = 0; i < t.length(); i++){
                if(s.charAt(idx) == t.charAt(i))
                    idx++;

                if(idx == s.length())
                    break;
            }

            if(idx == s.length()) System.out.println("Yes");
            else System.out.println("No");
        }
    }

}
