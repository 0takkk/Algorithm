import java.io.*;
import java.util.*;

public class Main {

    public static int n, idx, MAX;
    public static int arr[][], order[];
    public static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n][9];
        order = new int[9];
        visited = new boolean[9];
        order[3] = 0;
        visited[0] = true;

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 9; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        makeOrder(0);

        System.out.println(MAX);
    }

    public static void makeOrder(int cnt){
        if(cnt == 9){
            idx = 0;
            int ans = 0;
            for(int i = 0; i < n; i++){
                ans += game(i);
            }

            MAX = Math.max(MAX, ans);
            return;
        }

        for(int i = 0; i < 9; i++){
            if(!visited[i]){
                visited[i] = true;
                order[cnt] = i;

                if(cnt == 2) makeOrder(cnt+2);
                else makeOrder(cnt+1);

                visited[i] =  false;
            }
        }
    }

    public static int game(int inning){
        int out = 0;
        int score = 0;
        boolean[] field = new boolean[4];

        while(out < 3){
            int result = arr[inning][order[idx++]];
            if(idx == 9) idx = 0;

            if(result == 0) out++;
            else if(result == 1){
                if(field[3]) score++;

                for(int i = 3; i >= 2; i--){
                    field[i] = field[i-1];
                }

                field[1] = true;
            }
            else if(result == 2){
                if(field[3]) score++;
                if(field[2]) score++;

                field[3] = field[1];
                field[1] = false;
                field[2] = true;
            }
            else if(result == 3){
                if(field[3]) score++;
                if(field[2]) score++;
                if(field[1]) score++;

                for(int i = 0; i < 4; i++){
                    field[i] = false;
                }

                field[3] = true;
            }
            else{
                if(field[3]) score++;
                if(field[2]) score++;
                if(field[1]) score++;
                score++;

                for(int i = 0; i < 4; i++){
                    field[i] = false;
                }
            }
        }

        return score;
    }

}
