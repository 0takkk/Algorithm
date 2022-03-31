import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(t-->0){
            char[] word = br.readLine().toCharArray();

            int idx1 = -1;
            int idx2 = -1;

            for(int i = word.length-1; i > 0; i--){
                if(word[i-1] < word[i]){
                    idx1 = i-1;
                    break;
                }
            }

            if(idx1 == -1){
                for(int i = 0; i < word.length; i++){
                    sb.append(word[i]);
                }
            }else {
                for(int i = word.length-1; i >= 0; i--){
                    if(word[idx1] < word[i]){
                        idx2 = i;
                        break;
                    }
                }

                char tmp = word[idx1];
                word[idx1] = word[idx2];
                word[idx2] = tmp;

                Arrays.sort(word, idx1+1, word.length);

                for(int i = 0; i < word.length; i++){
                    sb.append(word[i]);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

}
