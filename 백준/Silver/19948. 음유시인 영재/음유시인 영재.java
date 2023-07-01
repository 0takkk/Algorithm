import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String poem = br.readLine();
        int spaceCount = Integer.parseInt(br.readLine());

        int[] alphabets = new int[26];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 26; i++){
            alphabets[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = true;

        for(int i = 0; i < poem.length(); i++){
            char c = poem.charAt(i);

            if(isSpace(c)) {
                if(spaceCount > 0) spaceCount--;
                else{
                    flag = false;
                    break;
                }
            }
            else {
                char alphabet = c;
                if(isUpper(c)) alphabet += 32;

                if(alphabets[alphabet - 'a'] > 0) alphabets[alphabet - 'a']--;
                else{
                    flag = false;
                    break;
                }
            }

            for(int j = i+1; j < poem.length(); j++){
                if(c == poem.charAt(j)) i = j;
                else break;
            }
        }

        if(!flag) {
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        String[] str = poem.split(" ");
        for (String s : str) {
            if(s.length() == 0) continue;
            char c = s.charAt(0);

            if(isUpper(c)) c += 32;
            
            if(alphabets[c - 'a'] <= 0){
                System.out.println(-1);
                return;
            }
            
            alphabets[c - 'a']--;
            sb.append(s.charAt(0));
        }

        System.out.println(sb.toString().toUpperCase());
    }

    public static boolean isSpace(char c){
        return c == ' ';
    }

    public static boolean isUpper(char c){
        return c >= 65 && c <= 90;
    }

}
