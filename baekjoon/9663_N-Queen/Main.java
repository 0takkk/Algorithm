import java.io.*;
import java.util.*;

public class Main {

    public static int n, ans;
    public static int[] queen;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        queen = new int[n];

        backtracking(0);
        System.out.println(ans);
    }

    public static void backtracking(int row){
        if(row == n){
            ans++;
            return;
        }

        for(int i = 0; i < n; i++){
            queen[row] = i;
            if(check(row)) backtracking(row+1);
        }
    }

    public static boolean check(int row){
        for(int i = 0; i < row; i++){
            if(queen[row] == queen[i]) return false;
            else if(Math.abs(row-i) == Math.abs(queen[row] - queen[i])) return false;
        }
        return true;
    }

}
