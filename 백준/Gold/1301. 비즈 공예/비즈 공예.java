import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static int[] arr;
    public static long[][][][][][][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        dp = new long[5][5][11][11][11][11][11];

        n = Integer.parseInt(br.readLine());
        arr = new int[5];

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for(int a = 0; a < 5; a++) {
            for(int b = 0; b < 5; b++) {
                for(int c = 0; c < 11; c++) {
                    for(int d = 0; d < 11; d++) {
                        for(int e = 0; e < 11; e++){
                            for(int f = 0; f < 11; f++) {
                                for(int g = 0; g < 11; g++){
                                    dp[a][b][c][d][e][f][g] = -1;
                                }
                            }
                        }
                    }
                }
            }
        }

        long result = 0;

        for(int a = 0; a < n; a++) {
            for(int b = 0; b < n; b++) {
                if(a == b) continue;
                arr[a]--;
                arr[b]--;
                result += makeDp(a, b);
                arr[a]++;
                arr[b]++;
            }
        }

        System.out.println(result);
    }

    public static long makeDp(int a, int b) {
        long result = dp[a][b][arr[0]][arr[1]][arr[2]][arr[3]][arr[4]];

        if(result != -1) return result;
        else if(arr[0]+arr[1]+arr[2]+arr[3]+arr[4] == 0) return 1;
        else {
            result = 0;
            for(int i = 0; i < n; i++) {
                if(arr[i] != 0 && a != i && b != i) {
                    arr[i]--;
                    result += makeDp(b, i);
                    arr[i]++;
                }
            }
        }
        return dp[a][b][arr[0]][arr[1]][arr[2]][arr[3]][arr[4]] = result;
    }
}
