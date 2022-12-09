import java.io.*;
import java.util.*;

public class Main {

    public static char[] vowel = {'a', 'i', 'y', 'e', 'o', 'u'};
    public static char[] consonant = {'b', 'k', 'x', 'z', 'n', 'h', 'd', 'c',
            'w', 'g', 'p', 'v', 'j', 'q', 't', 's', 'r', 'l', 'm', 'f'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        HashSet<Character> vowelSet = new HashSet<>();
        for (char c : vowel) {
            vowelSet.add(c);
        }

        int[] alpaIndex = new int[26];
        for(int i = 0; i < vowel.length; i++){
            alpaIndex[vowel[i] - 'a'] = i;
        }
        for(int i = 0; i < consonant.length; i++){
            alpaIndex[consonant[i] - 'a'] = i;
        }

        StringBuilder sb = new StringBuilder();

        String str = "";

        while((str = br.readLine()) != null) {
            char[] input = str.toCharArray();

            for (int i = 0; i < input.length; i++) {
                char c = input[i];
                if (!isAlpa(c)) continue;

                boolean upper = isUpper(c);
                char C = c;
                if (upper) C = (char) (c + 32);

                int idx = alpaIndex[C - 'a'];
                char decode;

                if (vowelSet.contains(C)) {
                    int originIdx = (idx + 6 - 3) % 6;
                    decode = vowel[originIdx];
                } else {
                    int originIdx = (idx + 20 - 10) % 20;
                    decode = consonant[originIdx];
                }

                if (upper) decode = (char) (decode - 32);
                input[i] = decode;
            }

            for (char c : input) {
                sb.append(c);
            }

            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean isAlpa(char c){
        return isUpper(c) || (c >= 97 && c <= 122);
    }
  
    public static boolean isUpper(char c){
        return c >= 65 && c <= 90;
    }
  
}
