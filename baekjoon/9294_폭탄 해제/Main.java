import java.io.*;
import java.util.*;

public class Main {

    public static char[][][] num = {
            {{'*', '*', '*'}, {'*', ' ', '*'}, {'*', ' ', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}},
            {{' ', ' ', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}},
            {{'*', '*', '*'}, {' ', ' ', '*'}, {'*', '*', '*'}, {'*', ' ', ' '}, {'*', '*', '*'}},
            {{'*', '*', '*'}, {' ', ' ', '*'}, {'*', '*', '*'}, {' ', ' ', '*'}, {'*', '*', '*'}},
            {{'*', ' ', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}},
            {{'*', '*', '*'}, {'*', ' ', ' '}, {'*', '*', '*'}, {' ', ' ', '*'}, {'*', '*', '*'}},
            {{'*', '*', '*'}, {'*', ' ', ' '}, {'*', '*', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}},
            {{'*', '*', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}, {' ', ' ', '*'}},
            {{'*', '*', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}},
            {{'*', '*', '*'}, {'*', ' ', '*'}, {'*', '*', '*'}, {' ', ' ', '*'}, {'*', '*', '*'}},
    };

    public static int len;
    public static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new char[5][32];
        for(int i = 0; i < 5; i++){
            arr[i] = br.readLine().toCharArray();
            len = Math.max(len, arr[i].length);
        }

        if((len + 1) % 4 != 0){
            System.out.println("BOOM!!");
            return;
        }

        StringBuilder sb = new StringBuilder();

        for(int j = 0; j < len; j+=4){
            int val = match(j);
            if(val == -1) {
                System.out.println("BOOM!!");
                return;
            }

            sb.append(val);
        }

        int number = Integer.parseInt(sb.toString());
        System.out.println(number % 6 == 0 ? "BEER!!" : "BOOM!!");
    }

    public static int match(int idx){
        for(int number = 0; number < 10; number++){
            boolean flag = true;

            for(int i = 0; i < 5; i++) {
                for (int j = idx; j < idx + 3; j++) {
                    if(num[number][i][j-idx] != arr[i][j]){
                        flag = false;
                        break;
                    }
                }
                if(!flag) break;
            }

            if(flag) return number;
        }

        return -1;
    };

}
