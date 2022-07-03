import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        boolean[][] arr = new boolean[52][52];

        for(int x = 0; x < n; x++){
            String str = br.readLine();
            int a = str.charAt(0) - 'A' <= 25 ? str.charAt(0) - 'A' : str.charAt(0) - 'A' - 6;
            int b = str.charAt(5) - 'A' <= 25 ? str.charAt(5) - 'A' : str.charAt(5) - 'A' - 6;

            arr[a][b] = true;
        }

        for(int k = 0; k < 52; k++){
            for(int i = 0; i < 52; i++){
                for(int j = 0; j < 52; j++){
                    if(arr[i][j] || i == j) continue;
                    // i => k 이고, k => j 이면 i => j
                    arr[i][j] = arr[i][k] && arr[k][j];
                }
            }
        }

        int count = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 52; i++){
            for(int j = 0; j < 52; j++){
                if(i == j) continue;

                if(arr[i][j]){
                    count++;

                    char a, b;
                    if(i < 26) a = (char)('A' + i);
                    else a = (char)('A' + i + 6);

                    if(j < 26) b = (char)('A' + j);
                    else b = (char)('A' + j + 6);

                    sb.append(a + " => " + b + "\n");
                }
            }
        }

        System.out.println(count);
        System.out.println(sb.toString());
    }

}
