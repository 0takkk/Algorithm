import java.io.*;
import java.util.*;

public class Main {

    public static HashSet<String> word;
    public static char[][][] board;
    public static int maxScore, count;
    public static String longString;
    public static int[] scores;
    public static char[] peeked;
    public static HashSet<String> visitedWord;
    public static boolean[][] visited;

    public static int[] dx = {0, 0, 1, -1, -1, -1, 1, 1};
    public static int[] dy = {1, -1, 0 ,0, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        word = new HashSet<>();
        scores = new int[]{0, 0, 0, 1, 1, 2, 3, 5, 11};
        int n = Integer.parseInt(br.readLine());

        while(n-->0){
            word.add(br.readLine());
        }

        br.readLine();

        int b = Integer.parseInt(br.readLine());
        board = new char[b][4][4];

        for(int k = 0; k < b; k++){
            for(int i = 0; i < 4; i++){
                board[k][i] = br.readLine().toCharArray();
            }

            if(k != b-1) br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < b; t++){
            maxScore = 0;
            longString = "";
            count = 0;
            visitedWord = new HashSet<>();
            peeked = new char[8];
            visited = new boolean[4][4];

            for(int i = 0; i < 4; i++){
                for(int j = 0; j < 4; j++){
                    visited[i][j] = true;
                    dfs(0, i, j, t);
                    visited[i][j] = false;
                }
            }

            sb.append(maxScore).append(" ");
            if(!longString.equals("")) sb.append(longString).append(" ");
            sb.append(count).append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void dfs(int idx, int x, int y, int b){
        if(idx == 8) return;

        peeked[idx] = board[b][x][y];

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= idx; i++){
            sb.append(peeked[i]);
        }
        String string = sb.toString();

        if(!visitedWord.contains(string) && word.contains(string)){
            visitedWord.add(string);
            maxScore += scores[idx+1];
            compareLongString(string);
            count++;
        }

        for(int i = 0; i < 8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(!isRange(nx, ny)) continue;

            if(!visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(idx + 1, nx, ny, b);
                visited[nx][ny] = false;
            }
        }

    }

    private static void compareLongString(String string) {
        if(string.length() > longString.length()){
            longString = string;
        }
        else if(string.length() == longString.length()){
            if(string.compareTo(longString) < 0) longString = string;
        }
    }

    public static boolean isRange(int x, int y){
        return x >= 0 && x < 4 && y >= 0 && y < 4;
    }

}
