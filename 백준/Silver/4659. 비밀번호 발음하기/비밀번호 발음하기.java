import java.io.*;
import java.util.*;

public class Main {

    public static HashSet<Character> vowel = new HashSet<>(List.of('a','e','i','o','u'));

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String password = "";
        StringBuilder sb = new StringBuilder();

        while (true) {
            password = br.readLine();
            if(password.equals("end")) break;
            boolean goodPassword = isGoodPassword(password);

            sb.append("<").append(password).append("> is ");
            if(goodPassword) {
                sb.append("acceptable.\n");
            }
            else {
                sb.append("not acceptable.\n");
            }
        }

        System.out.println(sb.toString());
    }

    public static boolean isGoodPassword(String password) {
        int vowelCount = 0;
        int consonantCount = 0;
        boolean isContainsVowel = false;

        for(int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);

            if(vowel.contains(c)) {
                isContainsVowel = true;
                vowelCount++;
                consonantCount = 0;
            } else {
                consonantCount++;
                vowelCount = 0;
            }

            if(i > 0 && c == password.charAt(i-1)) {
                if(c == 'e' || c == 'o') continue;
                return false;
            }
            if(vowelCount >= 3 || consonantCount >= 3) return false;
        }

        return isContainsVowel;
    }

}
