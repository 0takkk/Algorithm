import java.io.*;
import java.util.*;

public class Main {

    public static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String str = "";
        StringBuilder sb = new StringBuilder();
        while(!(str = br.readLine()).equals("end")){
            map = new char[3][3];

            for(int i = 0; i < 9; i++){
                map[i/3][i%3] = str.charAt(i);
            }

            if(check()) sb.append("valid\n");
            else sb.append("invalid\n");
        }

        System.out.println(sb.toString());
    }

    public static boolean check(){
        int xCnt = 0;
        int oCnt = 0;

        for(int i = 0; i < 9; i++){
            if(map[i/3][i%3] == 'X') xCnt++;
            else if(map[i/3][i%3] == 'O') oCnt++;
        }

        if(xCnt + oCnt == 9){
            if(xCnt != oCnt+1 || isFinish('O')) return false;
            return true;
        }
        else{
            if(xCnt == oCnt + 1){
                if(isFinish('X') && !isFinish('O')) return true;
                return false;
            }
            else if(xCnt == oCnt){
                if(!isFinish('X') && isFinish('O')) return true;
                return false;
            }
        }

        return false;
    }

    public static boolean isFinish(char c){
        for(int i = 0; i < 3; i++){
            if(map[i][0] == c && map[i][1] == c && map[i][2] == c) return true;
        }

        for(int i = 0; i < 3; i++){
            if(map[0][i] == c && map[1][i] == c && map[2][i] == c) return true;
        }

        if(map[0][0] == c && map[1][1] == c && map[2][2] == c) return true;

        if(map[0][2] == c && map[1][1] == c && map[2][0] == c) return true;

        return false;
    }

}
