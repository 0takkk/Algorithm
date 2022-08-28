import java.io.*;
import java.util.*;

public class Main {

    public static char[] wolf = {'w', 'o', 'l', 'f'};
    public static int[] count = new int[4];
    public static char[] alpa = new char[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = br.readLine();

        int idx = 1;
        char pre = str.charAt(0);
        alpa[0] = str.charAt(0);
        count[getIdx(str.charAt(0))]++;

        for(int i = 1; i < str.length(); i++){
            char now = str.charAt(i);

            if(now == pre){
                count[getIdx(now)]++;
                continue;
            }

            if(idx == 4){
                if(!check()) {
                    System.out.println(0);
                    return;
                }

                count = new int[4];
                idx = 0;
            }

            count[getIdx(now)]++;
            alpa[idx++] = now;
            pre = now;
        }

        if(!check()) System.out.println(0);
        else System.out.println(1);
    }

    public static boolean check(){
        int n = count[0];
        for(int i = 0; i < 4; i++){
            if(count[i] != n) return false;
            if(wolf[i] != alpa[i]) return false;
        }
        return true;
    }

    public static int getIdx(char c){
        if(c == 'w') return 0;
        else if(c == 'o') return 1;
        else if(c == 'l') return 2;
        else return 3;
    }

}
